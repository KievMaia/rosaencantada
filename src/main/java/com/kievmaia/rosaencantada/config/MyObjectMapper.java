package com.kievmaia.rosaencantada.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.DeserializationFeature;

public class MyObjectMapper {
    public static ObjectMapper create() {
        var mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
        return mapper;
    }
}
