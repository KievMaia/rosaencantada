package com.kievmaia.rosaencantada.rest.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(setterPrefix = "set")
@AllArgsConstructor
@NoArgsConstructor
public class SupplierRequestDTO {
    @Nullable
    private Long id;
    @NotNull
    private String name;
    @Nullable
    private String cnpj;
    @Nullable
    private String phone;
    @Nullable
    private String email;
    @Nullable
    private String address;
}

