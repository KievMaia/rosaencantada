package com.kievmaia.rosaencantada.rest.dto.subcategory;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(setterPrefix = "set")
@AllArgsConstructor
@NoArgsConstructor
public class SubCategoryRequestDTO {
    @NotNull
    private String name;
    @NotNull
    private String categoryName;
}