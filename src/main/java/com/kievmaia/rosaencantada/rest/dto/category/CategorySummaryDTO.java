package com.kievmaia.rosaencantada.rest.dto.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(setterPrefix = "set")
@AllArgsConstructor
@NoArgsConstructor
public class CategorySummaryDTO {
    private Long id;
    private String name;
}
