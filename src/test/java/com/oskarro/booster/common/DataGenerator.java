package com.oskarro.booster.common;

import com.oskarro.booster.model.Meal;
import com.oskarro.booster.model.Product;
import com.oskarro.booster.repository.MealRepository;
import com.oskarro.booster.repository.ProductRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.Month;
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

    @Autowired
    public MealRepository mealRepository;

    @BeforeAll
    void setUp() {
        List<Product> products = generateProducts();
        productRepository.saveAll(products);
        List<Meal> meals = generateMeals(products);
        mealRepository.saveAll(meals);
    }

    @AfterAll
    void afterAll() {
        productRepository.deleteAll();
        mealRepository.deleteAll();
    }

    private static List<Product> generateProducts() {
        Product productOne = Product.builder().name("Ham").energy(420).protein(32.11).fat(12.2).carbs(7.5).price(13.00).build();
        Product productTwo = Product.builder().name("Bread").energy(311).protein(1.21).fat(3.2).carbs(56d).price(3.22).build();
        Product productThree = Product.builder().name("Coke").energy(310).protein(0.44).fat(1.5).carbs(5.2).price(2.00).build();
        Product productFour = Product.builder().name("Musli").energy(209).protein(4.50).fat(6.8).carbs(24.1).price(15.99).build();
        Product productFive = Product.builder().name("Milk").energy(197).protein(11.98).fat(9.83).carbs(21d).price(2.50).build();

        return new ArrayList<>(Arrays.asList(productOne, productTwo, productThree, productFour, productFive));
    }

    private static List<Meal> generateMeals(List<Product> products) {

        Meal mealOne = Meal.builder().product(products.get(0)).dateTime(LocalDateTime.of(2022, Month.JULY, 21, 14, 31)).portion(2.40).build();
        Meal mealTwo = Meal.builder().product(products.get(0)).dateTime(LocalDateTime.of(2022, Month.APRIL, 1, 11, 32)).portion(3.10).build();
        Meal mealThree = Meal.builder().product(products.get(1)).dateTime(LocalDateTime.of(2021, Month.APRIL, 6, 18, 13)).portion(0.40).build();
        Meal mealFour = Meal.builder().product(products.get(2)).dateTime(LocalDateTime.of(2021, Month.AUGUST, 22, 12, 53)).portion(6.20).build();
        Meal mealFive = Meal.builder().product(products.get(3)).dateTime(LocalDateTime.of(2020, Month.SEPTEMBER, 30, 5, 44)).portion(7.77).build();
        Meal mealSix = Meal.builder().product(products.get(4)).dateTime(LocalDateTime.of(2022, Month.OCTOBER, 11, 16, 2)).portion(0.78).build();

        return new ArrayList<>(Arrays.asList(mealOne, mealTwo, mealThree, mealFour, mealFive, mealSix));
    }


}
