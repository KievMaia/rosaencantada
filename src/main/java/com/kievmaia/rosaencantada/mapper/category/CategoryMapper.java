package com.kievmaia.rosaencantada.mapper.category;

import com.kievmaia.rosaencantada.db.entity.Category;
import com.kievmaia.rosaencantada.db.entity.Supplier;
import com.kievmaia.rosaencantada.rest.dto.category.CategoryRequestDTO;
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

    public SupplierResponseDTO entityToResponseDTO(Supplier entity) {
        return SupplierResponseDTO.builder()
                .setId(entity.getId())
                .setName(entity.getName())
                .setCnpj(entity.getCnpj())
                .setPhone(entity.getPhone())
                .setEmail(entity.getEmail())
                .setAddress(entity.getAddress())
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

    public CategoryRequestDTO toUpdatedDTO(CategoryRequestDTO newDto) {
        return CategoryRequestDTO.builder()
                .setName(newDto.getName())
                .build();
    }
}

