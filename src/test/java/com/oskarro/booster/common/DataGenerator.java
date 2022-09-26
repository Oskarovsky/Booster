package com.oskarro.booster.common;

import com.oskarro.booster.model.Meal;
import com.oskarro.booster.model.Product;
import com.oskarro.booster.model.Provider;
import com.oskarro.booster.repository.MealRepository;
import com.oskarro.booster.repository.ProductRepository;
import com.oskarro.booster.repository.ProviderRepository;
import org.junit.jupiter.api.*;
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

    @Autowired
    public ProviderRepository providerRepository;

    @BeforeAll
    void setUp() {
        mealRepository.deleteAll();
        productRepository.deleteAll();
        providerRepository.deleteAll();

        List<Provider> providers = generateProviders();
        providerRepository.saveAll(providers);
        List<Product> products = generateProducts(providers);
        productRepository.saveAll(products);
        List<Meal> meals = generateMeals(products);
        mealRepository.saveAll(meals);
    }

    private static List<Provider> generateProviders() {
        return Arrays.asList(
          Provider.builder().name("FirstProv").build(),
          Provider.builder().name("SecondProv").build(),
          Provider.builder().name("ThirdProv").build(),
          Provider.builder().name("ForthProv").build()
        );
    }

    private static List<Product> generateProducts(List<Provider> providers) {
        return List.of(
                Product.builder().name("Ham").energy(420).protein(32.11).fat(12.2).carbs(7.5).price(13.00)
                        .provider(providers.get(0)).build(),
                Product.builder().name("Bread").energy(311).protein(1.21).fat(3.2).carbs(56d).price(3.22)
                        .provider(providers.get(2)).build(),
                Product.builder().name("Coke").energy(310).protein(0.44).fat(1.5).carbs(5.2).price(2.00)
                        .provider(providers.get(1)).build(),
                Product.builder().name("Musli").energy(209).protein(4.50).fat(6.8).carbs(24.1).price(15.99)
                        .provider(providers.get(0)).build(),
                Product.builder().name("Milk").energy(197).protein(11.98).fat(9.83).carbs(21d).price(2.50)
                        .provider(providers.get(3)).build()
        );
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
