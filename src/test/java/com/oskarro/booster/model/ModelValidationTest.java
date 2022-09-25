package com.oskarro.booster.model;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModelValidationTest {


    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();

    @Test
    public void test_shouldThrowErrorDueToValidation_Meal() {
        // GIVEN
        Meal meal = Meal.builder().id(1).portion(10.1).customerId(1).dateTime(null).build();

        // WHEN
        Set<ConstraintViolation<Meal>> violations = validator.validate(meal);
        ConstraintViolation<Meal> violation = violations.iterator().next();
        String failedPropertyName = violation.getPropertyPath().iterator().next().getName();

        // THEN
        assertAll(
                () -> assertEquals(1, violations.size()),
                () -> assertEquals("dateTime", failedPropertyName),
                () -> assertEquals(violation.getMessage(), "DateTime attribute cannot be null")
        );
    }

    @Test
    public void test_shouldThrowErrorDueToValidation_Product() {
        // GIVEN
        Product product = Product.builder().id(1).name("a").energy(null).build();

        // WHEN
        Set<ConstraintViolation<Product>> violations = validator.validate(product);

        List<String> messages = violations.stream()
                .map(constraintViolation -> String.format("%s %s",
                        constraintViolation.getPropertyPath().iterator().next().getName(),
                        constraintViolation.getMessage()))
                .toList();

        // THEN
        assertAll(
                () -> assertEquals(2, violations.size())
        );
    }

    @Test
    public void test_shouldThrowErrorDueToValidation_Provider() {
        // GIVEN
        Provider provider = Provider.builder().id(1).name(null).city("Warsaw").build();

        // WHEN
        Set<ConstraintViolation<Provider>> violations = validator.validate(provider);
        ConstraintViolation<Provider> violation = violations.iterator().next();
        String failedPropertyName = violation.getPropertyPath().iterator().next().getName();

        // THEN
        assertAll(
                () -> assertEquals(1, violations.size()),
                () -> assertEquals("name", failedPropertyName),
                () -> assertEquals(violation.getMessage(), "Provider must have a given name")
        );
    }
}
