package com.kievmaia.rosaencantada.integrationtests.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kievmaia.rosaencantada.integrationtests.testcontainers.AbstractIntegrationTest;
import com.kievmaia.rosaencantada.config.TestConfigs;
import com.kievmaia.rosaencantada.rest.dto.PagedResponse;
import com.kievmaia.rosaencantada.rest.dto.supplier.SupplierRequestDTO;
import com.kievmaia.rosaencantada.rest.dto.supplier.SupplierResponseDTO;
import com.kievmaia.rosaencantada.rest.dto.supplier.SupplierSummaryDTO;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.kievmaia.rosaencantada.funcional.supplier.builder.SupplierBuilder.buildSupplierRequestDTO;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class SupplierControllerIT extends AbstractIntegrationTest {

    @Autowired
    private ObjectMapper objectMapper;
    private static RequestSpecification specification;
    private static SupplierRequestDTO requestDTO;
    private static SupplierSummaryDTO createdSupplier;

    @BeforeAll
    public static void setUp() {
        specification = new RequestSpecBuilder()
                .setBasePath("/v1/suppliers")
                .setPort(TestConfigs.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        requestDTO = buildSupplierRequestDTO();

        createdSupplier = new SupplierSummaryDTO();
    }

    @Test
    @Order(1)
    void testCreateSupplier() throws JsonProcessingException {
        var content = given().spec(specification)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .body(requestDTO)
                .when()
                .post()
                .then()
                .statusCode(200)
                .extract()
                .body().asString();

        createdSupplier = objectMapper.readValue(content, SupplierSummaryDTO.class);

        assertNotNull(createdSupplier);
        assertThat(createdSupplier.getName()).isEqualTo("Fornecedor Teste");
    }

    @Test
    @Order(2)
    void testGetSupplierById() throws JsonProcessingException {
        var content = given().spec(specification)
                .when()
                .get("/{id}", createdSupplier.getId())
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        var supplierSummaryDTORecovered = objectMapper.readValue(content, SupplierSummaryDTO.class);

        assertNotNull(supplierSummaryDTORecovered);
        assertThat(createdSupplier.getName()).isEqualTo("Fornecedor Teste");
    }

    @Test
    @Order(3)
    void testGetAllSuppliers() throws JsonProcessingException {
        var content = given().spec(specification)
                .when()
                .get("/all")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        var pagedResponse = objectMapper.readValue(
                content, new TypeReference<PagedResponse<SupplierResponseDTO>>() {
                });

        var supplierList = pagedResponse.getContent();

        assertNotNull(supplierList);
        assertThat(supplierList).isNotEmpty();
        assertThat(supplierList.get(0).getName()).isEqualTo("Fornecedor Teste");
    }

    @Test
    @Order(4)
    void testUpdateSupplier() throws Exception {
        var updatedRequestDTO = buildSupplierRequestDTO();
        updatedRequestDTO.setName("Fornecedor Teste Atualizado");

        var content = given().spec(specification)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .body(updatedRequestDTO)
                .when()
                .put("/{id}", createdSupplier.getId())
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        var updatedSupplierSummaryDTO = objectMapper.readValue(content, SupplierSummaryDTO.class);

        assertNotNull(updatedSupplierSummaryDTO);
        assertThat(updatedSupplierSummaryDTO.getName()).isEqualTo("Fornecedor Teste Atualizado");
    }

    @Test
    @Order(5)
    void testDeleteSupplier() {
        given().spec(specification)
                .when()
                .delete("/{id}", createdSupplier.getId())
                .then()
                .statusCode(204);

        given().spec(specification)
                .when()
                .get("/{id}", createdSupplier.getId())
                .then()
                .statusCode(404);
    }
}
