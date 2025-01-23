package com.kievmaia.rosaencantada.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(setterPrefix = "set")
@AllArgsConstructor
@NoArgsConstructor
public class SupplierRequestDTO {
    private String name;
    private String cnpj;
    private String phone;
    private String email;
    private String address;
}

