package com.kievmaia.rosaencantada.handler;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.kievmaia.rosaencantada.handler.exception.EntityNotFoundException;
import com.kievmaia.rosaencantada.handler.exception.ValidationErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<StandardError> handleProductNotFound(EntityNotFoundException ex) {
        var standardError = new StandardError(
                HttpStatus.NOT_FOUND.value(),
                "Not Found",
                ex.getMessage(),
                LocalDateTime.now().toString()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standardError);
    }

    @ExceptionHandler(UnrecognizedPropertyException.class)
    public ResponseEntity<ValidationErrorResponse> handleUnrecognizedPropertyException(UnrecognizedPropertyException ex) {
        var invalidFields = ex.getPath()
                .stream()
                .map(JsonMappingException.Reference::getFieldName)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        var errorResponse = ValidationErrorResponse.builder()
                .message("Invalid fields: ")
                .invalidFields(invalidFields)
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
