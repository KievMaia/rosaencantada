package com.kievmaia.rosaencantada.funcional.supplier.builder;

import com.kievmaia.rosaencantada.rest.dto.supplier.SupplierRequestDTO;

public class SupplierBuilder {
    public static SupplierRequestDTO buildSupplierRequestDTO() {
        var requestDTO = new SupplierRequestDTO();
        requestDTO.setName("Fornecedor Teste");
        requestDTO.setCnpj("12345678000199");
        return requestDTO;
    }
}
