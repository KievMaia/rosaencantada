package com.kievmaia.rosaencantada.rest.dto.category;

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
public class CategoryRequestDTO {
    @Nullable
    private Long id;
    @NotNull
    private String name;
}