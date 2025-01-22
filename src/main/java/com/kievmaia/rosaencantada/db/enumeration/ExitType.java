package com.kievmaia.rosaencantada.db.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum ExitType {
    VENDA("Sale"),
    TROCA("Exchange");

    private final String translation;

    public static ExitType getTranslation(String translation) {
        return Arrays.stream(ExitType.values())
                .filter(item -> item.getTranslation().equals(translation))
                .findFirst()
                .orElseThrow(
                        () -> new IllegalArgumentException("Não foi possível obter o tipo de entrada"));
    }
}