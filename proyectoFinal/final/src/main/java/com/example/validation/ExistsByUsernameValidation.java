package com.example.validation;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.services.UserService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ExistsByUsernameValidation implements ConstraintValidator<ExistsByUsername, String>{

    @Autowired
    private UserService userService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (userService == null) {
            return true;
        }
        return !userService.existsByUsername(value);
    }

}
