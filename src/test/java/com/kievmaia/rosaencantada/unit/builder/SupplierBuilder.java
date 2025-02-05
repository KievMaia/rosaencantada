package com.kievmaia.rosaencantada.unit.builder;

import com.kievmaia.rosaencantada.db.entity.Supplier;
import com.kievmaia.rosaencantada.rest.dto.supplier.SupplierRequestDTO;
import com.kievmaia.rosaencantada.rest.dto.supplier.SupplierResponseDTO;

import java.time.LocalDateTime;

public class SupplierBuilder {
    public static SupplierRequestDTO buildSupplierRequestDTO() {
        var requestDTO = new SupplierRequestDTO();
        requestDTO.setName("Fornecedor Teste");
        requestDTO.setCnpj("12345678000199");
        return requestDTO;
    }

    public static SupplierRequestDTO getSupplierRequestDTO() {
        return SupplierRequestDTO.builder()
                .setName("Test Supplier")
                .setCnpj("12345678901234")
                .setPhone("1234567890")
                .setEmail("test@supplier.com")
                .setAddress("123 Test Street")
                .build();
    }

    public static Supplier buildSupplierEntity() {
        return Supplier.builder()
                .setId(1L)
                .setName("Test Supplier")
                .setCnpj("12345678901234")
                .setPhone("1234567890")
                .setEmail("test@supplier.com")
                .setAddress("123 Test Street")
                .setCreatedDate(LocalDateTime.now())
                .setUpdatedDate(LocalDateTime.now())
                .build();
    }

    public static SupplierRequestDTO buildNullValuesSupplierRequestDTO() {
        return SupplierRequestDTO.builder()
                .setName(null)
                .setCnpj(null)
                .setPhone(null)
                .setEmail(null)
                .setAddress(null)
                .build();
    }

    public static SupplierResponseDTO buildSupplierResponseDTO() {
        return SupplierResponseDTO.builder()
                .setName("Existing Name")
                .setCnpj("12345678901234")
                .setPhone("1234567890")
                .setEmail("existing@email.com")
                .setAddress("Existing Address")
                .build();
    }

    public static SupplierRequestDTO buildSupplierRequestDTOToUpdate() {
        return SupplierRequestDTO.builder()
                .setName("New Name")
                .setCnpj(null)
                .setPhone("9876543210")
                .setEmail(null)
                .setAddress("New Address")
                .build();
    }

    public static SupplierRequestDTO buildSupplierRequestDTOWithSpecialCharacters() {
        return SupplierRequestDTO.builder()
                .setName("New Name with Spécial Chàracters!")
                .setCnpj("98.765.432/0001-00")
                .setPhone("+55 (11) 98765-4321")
                .setEmail("new.email+special@domain.com")
                .setAddress("Rua das Flôres, nº 123 - Bairro Jardim")
                .build();
    }
}
