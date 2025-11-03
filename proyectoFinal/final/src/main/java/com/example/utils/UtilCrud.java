package com.example.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public class UtilCrud {
    public static ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(error -> {
            errors.put(error.getField(), "En el campo " + error.getField() + " - " + error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}