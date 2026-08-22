package com.qa.gorest.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

public class JsonDataReader {

    public static <T> T readJson(String path, Class<T> clazz) {
        try {
            return new ObjectMapper().readValue(new File(path), clazz);
        } catch (Exception e) {
            throw new RuntimeException("Unable to read json: " + path, e);
        }
    }
}
