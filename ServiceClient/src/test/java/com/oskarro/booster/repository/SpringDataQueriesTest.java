package com.oskarro.booster.repository;

import com.oskarro.booster.common.DataGenerator;
import com.oskarro.booster.model.Meal;
import com.oskarro.booster.model.Product;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpringDataQueriesTest extends DataGenerator {

    @Test
    void test_findAllProducts() {
        // WHEN
        List<Product> products = productRepository.findAll();

        // THEN
        assertEquals(5, products.size());
    }

    @Test
    void test_findProduct() {
        // WHEN
        Product product = productRepository.findByName("Milk");

        // THEN
        assertEquals("Milk", product.getName());
    }

    @Test
    void test_findAllProductsByOrderByNameAsc() {
        // WHEN
        List<Product> products = productRepository.findAllByOrderByNameAsc();

        // THEN
        assertAll(() -> assertEquals(5, products.size()),
                () -> assertEquals("Bread", products.get(0).getName()),
                () -> assertEquals("Musli", products.get(products.size() - 1).getName()));
    }

    @Test
    void test_findByDateTimeBetween() {
        // WHEN
        List<Meal> meals = mealRepository.findByDateTimeBetween(
                LocalDateTime.of(2022, 1, 1, 1, 1),
                LocalDateTime.of(2022, 12, 30, 1, 1));

        // THEN
        assertEquals(3, meals.size());

    }
}
