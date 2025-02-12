package com.kievmaia.rosaencantada.integrationtests.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kievmaia.rosaencantada.config.TestConfigs;
import com.kievmaia.rosaencantada.integrationtests.testcontainers.AbstractIntegrationTest;
import com.kievmaia.rosaencantada.rest.dto.PagedResponse;
import com.kievmaia.rosaencantada.rest.dto.category.CategoryRequestDTO;
import com.kievmaia.rosaencantada.rest.dto.category.CategoryResponseDTO;
import com.kievmaia.rosaencantada.rest.dto.category.CategorySummaryDTO;
import com.kievmaia.rosaencantada.rest.dto.supplier.SupplierSummaryDTO;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.kievmaia.rosaencantada.unit.builder.CategoryBuilder.buildCategoryRequestDTO;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class CategoryControllerIT extends AbstractIntegrationTest {

    @Autowired
    private ObjectMapper objectMapper;
    private static RequestSpecification specification;
    private static CategoryRequestDTO requestDTO;
    private static CategorySummaryDTO createdCategory;

    @BeforeAll
    public static void setUp() {
        specification = new RequestSpecBuilder()
                .setBasePath("/v1/categories")
                .setPort(TestConfigs.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        requestDTO = buildCategoryRequestDTO();

        createdCategory = new CategorySummaryDTO();
    }

    @Test
    @Order(1)
    void testCreateCategory() throws JsonProcessingException {
        var content = given().spec(specification)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .body(requestDTO)
                .when()
                .post()
                .then()
                .statusCode(200)
                .extract()
                .body().asString();

        createdCategory = objectMapper.readValue(content, CategorySummaryDTO.class);

        assertNotNull(createdCategory);
        assertThat(createdCategory.getName()).isEqualTo("Categoria");
    }

    @Test
    @Order(2)
    void testGetCategoryById() throws JsonProcessingException {
        var content = given().spec(specification)
                .when()
                .get("/{id}", createdCategory.getId())
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        var categorySummaryDTORecovered = objectMapper.readValue(content, CategorySummaryDTO.class);

        assertNotNull(categorySummaryDTORecovered);
        assertThat(createdCategory.getName()).isEqualTo(categorySummaryDTORecovered.getName());
    }

    @Test
    @Order(3)
    void testGetCategoryByName() throws JsonProcessingException {
        var content = given().spec(specification)
                .when()
                .get("/byname/{categoryName}", createdCategory.getName())
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        var categorySummaryDTORecovered = objectMapper.readValue(content, CategorySummaryDTO.class);

        assertNotNull(categorySummaryDTORecovered);
        assertThat(createdCategory.getName()).isEqualTo(categorySummaryDTORecovered.getName());
    }
//
    @Test
    @Order(4)
    void testGetAllCategories() throws JsonProcessingException {
        var content = given().spec(specification)
                .when()
                .get("/all")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        var pagedResponse = objectMapper.readValue(
                content, new TypeReference<PagedResponse<CategoryResponseDTO>>() {
                });

        var categoryList = pagedResponse.getContent();

        assertNotNull(categoryList);
        assertThat(categoryList).isNotEmpty();
        assertThat(categoryList.get(0).getName()).isEqualTo("Categoria");
    }

    @Test
    @Order(5)
    void testUpdateCategory() throws Exception {
        var updatedRequestDTO = buildCategoryRequestDTO();
        updatedRequestDTO.setName("Categoria Atualizada");

        var content = given().spec(specification)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .body(updatedRequestDTO)
                .when()
                .put("/{id}", createdCategory.getId())
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        var updatedCategorySummaryDTO = objectMapper.readValue(content, CategorySummaryDTO.class);

        assertNotNull(updatedCategorySummaryDTO);
        assertThat(updatedCategorySummaryDTO.getName()).isEqualTo(updatedRequestDTO.getName());
    }

    @Test
    @Order(6)
    void testDeleteSupplier() {
        given().spec(specification)
                .when()
                .delete("/{id}", createdCategory.getId())
                .then()
                .statusCode(204);

        given().spec(specification)
                .when()
                .get("/{id}", createdCategory.getId())
                .then()
                .statusCode(404);
    }
}
