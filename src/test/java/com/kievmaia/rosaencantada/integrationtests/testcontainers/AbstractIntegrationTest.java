package com.kievmaia.rosaencantada.integrationtests.testcontainers;

import jakarta.validation.constraints.NotNull;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.lifecycle.Startables;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Map;
import java.util.stream.Stream;

@ContextConfiguration(initializers = AbstractIntegrationTest.Initializer.class)
public class AbstractIntegrationTest {

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        static PostgreSQLContainer<?> postgresql = new PostgreSQLContainer<>("postgres:latest");

        private static void startContainers() {
            Startables.deepStart(Stream.of(postgresql)).join();
            executeSqlScript();
        }

        private static void executeSqlScript() {
            try (Connection connection = DriverManager.getConnection(postgresql.getJdbcUrl(), postgresql.getUsername(), postgresql.getPassword());
                 Statement statement = connection.createStatement()) {
                InputStream inputStream = AbstractIntegrationTest.class.getClassLoader().getResourceAsStream("db.migration/schema.sql");
                if (inputStream == null) {
                    throw new FileNotFoundException("Script not found: " + "db.migration/schema.sql");
                }
                String sql = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
                statement.execute(sql);
            } catch (Exception e) {
                throw new RuntimeException("Failed to execute SQL script", e);
            }
        }

        private static Map<String, String> createConnectionConfiguration() {
            return Map.of(
                    "spring.datasource.url", postgresql.getJdbcUrl(),
                    "spring.datasource.username", postgresql.getUsername(),
                    "spring.datasource.password", postgresql.getPassword());
        }

        @SuppressWarnings({"unchecked", "rawtypes"})
        @Override
        public void initialize(@NotNull ConfigurableApplicationContext applicationContext) {
            startContainers();
            ConfigurableEnvironment environment = applicationContext.getEnvironment();
            MapPropertySource testcontainers =
                    new MapPropertySource("testcontainers", (Map) createConnectionConfiguration());
            environment.getPropertySources().addFirst(testcontainers);
        }
    }
}