package com.oskarro.booster.common;

import com.oskarro.booster.model.Product;
import com.oskarro.booster.repository.ProductRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Base class for Spring Data tests
 * */

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class DataGenerator {

    @Autowired
    public ProductRepository productRepository;

    @BeforeAll
    void setUp() {
        productRepository.saveAll(generateProducts());
    }

    @AfterAll
    void afterAll() {
        productRepository.deleteAll();
    }

    private static List<Product> generateProducts() {
        Product productOne = Product.builder().name("Ham").energy(420).protein(32.11).fat(12.2).carbs(7.5).price(13.00).build();
        Product productTwo = Product.builder().name("Bread").energy(311).protein(1.21).fat(3.2).carbs(56d).price(3.22).build();
        Product productThree = Product.builder().name("Coke").energy(310).protein(0.44).fat(1.5).carbs(5.2).price(2.00).build();
        Product productFour = Product.builder().name("Musli").energy(209).protein(4.50).fat(6.8).carbs(24.1).price(15.99).build();
        Product productFive = Product.builder().name("Milk").energy(197).protein(11.98).fat(9.83).carbs(21d).price(2.50).build();

        return new ArrayList<>(Arrays.asList(productOne, productTwo, productThree, productFour, productFive));
    }


}
