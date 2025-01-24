package com.kievmaia.rosaencantada.handler.exception;

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
