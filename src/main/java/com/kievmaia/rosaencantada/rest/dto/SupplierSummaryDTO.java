package com.kievmaia.rosaencantada.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(setterPrefix = "set")
@AllArgsConstructor
@NoArgsConstructor
public class SupplierSummaryDTO {
    private Long id;
    private String name;
}
