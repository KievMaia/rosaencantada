package com.kievmaia.rosaencantada.unit.builder;

import com.kievmaia.rosaencantada.db.entity.Category;
import com.kievmaia.rosaencantada.db.entity.Supplier;
import com.kievmaia.rosaencantada.rest.dto.category.CategoryRequestDTO;
import com.kievmaia.rosaencantada.rest.dto.category.CategoryResponseDTO;
import com.kievmaia.rosaencantada.rest.dto.category.CategorySummaryDTO;
import com.kievmaia.rosaencantada.rest.dto.supplier.SupplierRequestDTO;
import com.kievmaia.rosaencantada.rest.dto.supplier.SupplierResponseDTO;

import java.time.LocalDateTime;

public class CategoryBuilder {
    public static CategoryRequestDTO buildCategoryRequestDTO() {
        return CategoryRequestDTO.builder()
                .setId(1L)
                .setName("Categoria")
                .build();
    }

//    public static SupplierRequestDTO getSupplierRequestDTO() {
//        return SupplierRequestDTO.builder()
//                .setName("Test Supplier")
//                .setCnpj("12345678901234")
//                .setPhone("1234567890")
//                .setEmail("test@supplier.com")
//                .setAddress("123 Test Street")
//                .build();
//    }
//
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
//
//    public static SupplierRequestDTO buildNullValuesSupplierRequestDTO() {
//        return SupplierRequestDTO.builder()
//                .setName(null)
//                .setCnpj(null)
//                .setPhone(null)
//                .setEmail(null)
//                .setAddress(null)
//                .build();
//    }
//
    public static CategoryResponseDTO buildCategoryResponseDTO() {
        return CategoryResponseDTO.builder()
                .setId(1L)
                .setName("Categoria")
                .build();
    }
//
//    public static SupplierRequestDTO buildSupplierRequestDTOToUpdate() {
//        return SupplierRequestDTO.builder()
//                .setName("New Name")
//                .setCnpj(null)
//                .setPhone("9876543210")
//                .setEmail(null)
//                .setAddress("New Address")
//                .build();
//    }
//
//    public static SupplierRequestDTO buildSupplierRequestDTOWithSpecialCharacters() {
//        return SupplierRequestDTO.builder()
//                .setName("New Name with Spécial Chàracters!")
//                .setCnpj("98.765.432/0001-00")
//                .setPhone("+55 (11) 98765-4321")
//                .setEmail("new.email+special@domain.com")
//                .setAddress("Rua das Flôres, nº 123 - Bairro Jardim")
//                .build();
//    }
}
