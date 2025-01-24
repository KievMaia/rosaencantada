package com.kievmaia.rosaencantada.handler.exception;

import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ValidationErrorResponse {

    private String message;
    private List<String> invalidFields;
}
