package com.kievmaia.rosaencantada.integrationtests.swagger;

import com.kievmaia.rosaencantada.integrationtests.testcontainers.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SwaggerIntegrationTest extends AbstractIntegrationTest {
    @Test
    void testShouldDisplaySwaggerUIPage() {
    }
}
