package com.kievmaia.rosaencantada.unit.builder;

import com.kievmaia.rosaencantada.db.entity.Category;
import com.kievmaia.rosaencantada.rest.dto.category.CategoryRequestDTO;
import com.kievmaia.rosaencantada.rest.dto.category.CategoryResponseDTO;
import com.kievmaia.rosaencantada.rest.dto.category.CategorySummaryDTO;

import java.time.LocalDateTime;

public class CategoryBuilder {
    public static CategoryRequestDTO buildCategoryRequestDTO() {
        return CategoryRequestDTO.builder()
                .setId(1L)
                .setName("Categoria")
                .build();
    }

    public static Category buildCategoryEntity() {
        return Category.builder()
                .setId(1L)
                .setName("Categoria")
                .setCreatedDate(LocalDateTime.now())
                .setUpdatedDate(LocalDateTime.now())
                .build();
    }

    public static CategorySummaryDTO buildCategorySummaryDTO() {
        return CategorySummaryDTO.builder()
                .setId(1L)
                .setName("Categoria")
                .build();
    }

    public static CategoryResponseDTO buildCategoryResponseDTO() {
        return CategoryResponseDTO.builder()
                .setId(1L)
                .setName("Categoria")
                .build();
    }
}
