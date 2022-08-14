package com.oskarro.booster.service;

import com.oskarro.booster.config.SpringDataConfiguration;
import com.oskarro.booster.model.Meal;
import com.oskarro.booster.model.Product;
import com.oskarro.booster.model.Provider;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringDataConfiguration.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MealServiceTest {

    @Autowired
    ProductService productService;

    @Autowired
    MealService mealService;

    @Autowired
    ProviderService providerService;

    @BeforeAll
    public void setUp() {
        // CREATING PROVIDERS
        Provider providerOne = new Provider();
        providerOne.setId(1);
        providerOne.setName("Prov1");
        Provider providerTwo = new Provider();
        providerTwo.setId(2);
        providerTwo.setName("Prov2");
        providerService.create(providerOne);
        providerService.create(providerTwo);

        // CREATING PRODUCTS
        Product productOne = new Product();
        productOne.setName("ProdName_First");
        productOne.setProviderById(1);
        productOne.setEnergy(310);
        Product productTwo = new Product();
        productTwo.setName("ProdName_Second");
        productTwo.setProviderById(1);
        productTwo.setEnergy(10);

        productService.create(productOne);
        productService.create(productTwo);
    }

    @Test
    public void test_fetchMealsFromPeriodOfTime() {
        // GIVEN
        Meal mealOne = Meal.builder()
                .dateTime(LocalDateTime.of(2019, 3, 28, 14, 33))
                .portion(1.5)
                .build();
        mealOne.setProductById(1);
        mealService.create(mealOne);

        // WHEN
        List<Meal> mealsResult = mealService.getMealFromPeriodOfTime(
                LocalDateTime.of(2018, 1, 1, 2, 1),
                LocalDateTime.of(2022, 1, 1, 1, 1));

        // THEN
        assertEquals(1, mealsResult.size());
    }
}
