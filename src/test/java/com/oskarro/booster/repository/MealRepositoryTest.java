package com.oskarro.booster.repository;

import com.oskarro.booster.model.Meal;
import com.oskarro.booster.model.Product;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MealRepositoryTest {

    @Autowired
    MealRepository mealRepository;

    @Autowired
    ProductRepository productRepository;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void test_saveMealEntity_and_saveProductEntity() {
        // GIVEN (Input)
        Product product = Product.builder().energy(420).protein(13.2).name("Bread").carbs(54.2).build();
        Product productCurrent = productRepository.save(product);

        // WHEN (Action)
        Meal meal = Meal.builder().portion(2d).dateTime(LocalDateTime.now()).product(productCurrent).build();
        Meal mealCurrent = mealRepository.save(meal);

        Product productResult = productRepository.findById(1).get();
        Meal mealResult = mealRepository.findById(1).get();

        // THEN (Output)
        assertThat(productResult).isNotNull();
        assertThat(mealResult).isNotNull();
        assertThat(mealResult.getProduct()).isNotNull();
        assertThat(mealResult.getProduct()).isEqualTo(productResult);
    }

    @Test
    @Order(2)
    public void test_getMeal() {
        // WHEN (Action)
        Meal mealResult = mealRepository.findById(1).get();

        // THEN (Output)
        assertThat(mealResult).isNotNull();
        assertThat(mealResult.getId()).isEqualTo(1);
    }

    @Test
    @Order(3)
    public void test_getListOfMeals() {
        // WHEN (Action)
        List<Meal> mealResult = mealRepository.findAll();

        // THEN (Output)
        assertThat(mealResult.size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    public void test_updateMeal() {
        // GIVEN (Input)
        Meal meal = mealRepository.findById(1).get();

        // WHEN (Action)
        meal.setPortion(30d);
        Meal mealCurrent = mealRepository.save(meal);

        // THEN (Output)
        assertThat(mealCurrent.getPortion()).isEqualTo(30d);
    }

    @Test
    @Order(5)
    public void test_deleteMeal() {
        // GIVEN (Input)
        Meal meal = mealRepository.findById(1).get();
        assertThat(meal).isNotNull();

        // WHEN (Action)
        mealRepository.delete(meal);
        Optional<Meal> optionalMeal = mealRepository.findById(1);
        Meal mealResult = null;

        if (optionalMeal.isPresent()) {
            mealResult = optionalMeal.get();
        }

        // THEN (Output)
        assertThat(mealResult).isNull();
    }
}
