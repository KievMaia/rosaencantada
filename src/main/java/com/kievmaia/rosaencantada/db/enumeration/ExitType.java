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

    public static EntryType getTranslation(String translation) {
        return Arrays.stream(EntryType.values())
                .filter(item -> item.getTranslation().equals(translation))
                .findFirst()
                .orElseThrow(
                        () -> new IllegalArgumentException("Não foi possível obter o tipo de entrada"));
    }
}