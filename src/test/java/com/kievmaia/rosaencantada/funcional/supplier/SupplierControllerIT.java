package com.kievmaia.rosaencantada.funcional.supplier;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kievmaia.rosaencantada.config.AbstractIntegrationTest;
import com.kievmaia.rosaencantada.config.TestConfigs;
import com.kievmaia.rosaencantada.rest.dto.SupplierSummaryDTO;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static com.kievmaia.rosaencantada.funcional.supplier.builder.SupplierBuilder.buildSupplierRequestDTO;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class SupplierControllerIT extends AbstractIntegrationTest {

    private static RequestSpecification specification;
    private static ObjectMapper objectMapper;

    @BeforeAll
    public static void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        specification = new RequestSpecBuilder()
                .setBasePath("/v1/suppliers")
                .setPort(TestConfigs.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();
    }

    @Test
    void testCreateSupplier() throws JsonProcessingException {
        var requestDTO = buildSupplierRequestDTO();

        var content = given().spec(specification)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .body(requestDTO)
                .when()
                .post()
                .then()
                .statusCode(200)
                .extract()
                .body().asString();

        var createdSupplier = objectMapper.readValue(content, SupplierSummaryDTO.class);

        assertNotNull(createdSupplier);
        assertThat(createdSupplier.getName()).isEqualTo("Fornecedor Teste");
    }
}
