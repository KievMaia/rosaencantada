package com.kievmaia.rosaencantada.rest.dto.supplier;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder(setterPrefix = "set")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SupplierResponseDTO {
    private Long id;
    private String name;
    private String cnpj;
    private String phone;
    private String email;
    private String address;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
