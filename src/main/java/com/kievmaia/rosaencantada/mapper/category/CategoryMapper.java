package com.kievmaia.rosaencantada.mapper.category;

import com.kievmaia.rosaencantada.db.entity.Category;
import com.kievmaia.rosaencantada.db.entity.Supplier;
import com.kievmaia.rosaencantada.rest.dto.category.CategoryRequestDTO;
import com.kievmaia.rosaencantada.rest.dto.category.CategoryResponseDTO;
import com.kievmaia.rosaencantada.rest.dto.category.CategorySummaryDTO;
import com.kievmaia.rosaencantada.rest.dto.supplier.SupplierResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public Category requestDTOToEntity(CategoryRequestDTO dto) {
        return Category.builder()
                .setName(dto.getName())
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

