package com.oskarro.booster.repository;

import com.oskarro.booster.common.DataGenerator;
import com.oskarro.booster.model.Product;
import org.junit.jupiter.api.Test;

import java.util.List;

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
}
