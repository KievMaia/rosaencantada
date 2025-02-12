package com.kievmaia.rosaencantada.handler.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntityNotFoundException extends RuntimeException {
    private final Object identifier;

    public EntityNotFoundException(String message, Object identifier) {
        super(String.format(message, identifier));
        this.identifier = identifier;
    }
}

