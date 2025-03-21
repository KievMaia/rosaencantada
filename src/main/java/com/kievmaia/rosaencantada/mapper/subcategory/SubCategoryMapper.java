package com.kievmaia.rosaencantada.mapper.subcategory;

import com.kievmaia.rosaencantada.db.entity.Category;
import com.kievmaia.rosaencantada.db.entity.SubCategory;
import com.kievmaia.rosaencantada.rest.dto.category.CategoryRequestDTO;
import com.kievmaia.rosaencantada.rest.dto.category.CategoryResponseDTO;
import com.kievmaia.rosaencantada.rest.dto.category.CategorySummaryDTO;
import com.kievmaia.rosaencantada.rest.dto.subcategory.SubCategoryRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class SubCategoryMapper {

    public SubCategory requestDTOToEntity(SubCategoryRequestDTO dto, Category category) {
        return SubCategory.builder()
                .setName(dto.getName())
                .setCategory(category)
                .build();
    }

    public CategoryResponseDTO entityToResponseDTO(Category entity) {
        return CategoryResponseDTO.builder()
                .setId(entity.getId())
                .setName(entity.getName())
                .setCreatedDate(entity.getCreatedDate())
                .setUpdatedDate(entity.getUpdatedDate())
                .build();
    }

    public CategorySummaryDTO entityToSummaryDTO(Category entity) {
        return CategorySummaryDTO.builder()
                .setId(entity.getId())
                .setName(entity.getName())
                .build();
    }

    public Category toUpdatedDTO(Category existingCategory, CategoryRequestDTO newDto) {
        return Category.builder()
                .setId(existingCategory.getId())
                .setName(newDto.getName())
                .build();
    }
}

