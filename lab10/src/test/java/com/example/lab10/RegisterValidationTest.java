package com.example.lab10;

import com.example.lab10.dto.RegisterRequest;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RegisterValidationTest {

    private Validator validator;

    @BeforeEach
    void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void shouldRejectWeakPassword() {
        RegisterRequest request = new RegisterRequest();
        request.setUsername("test");
        request.setEmail("test@test.com");
        request.setPassword("123"); // trop court

        var violations = validator.validate(request);

        assertFalse(violations.isEmpty());
    }
}