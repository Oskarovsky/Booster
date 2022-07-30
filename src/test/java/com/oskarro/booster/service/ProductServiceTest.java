package com.oskarro.booster.service;

import com.oskarro.booster.config.SpringDataConfiguration;
import com.oskarro.booster.model.Meal;
import com.oskarro.booster.model.Product;
import com.oskarro.booster.repository.MealRepository;
import com.oskarro.booster.repository.ProductRepository;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringDataConfiguration.class})
public class ProductServiceTest {

    @Autowired
    ProductService productService;

    @Autowired
    MealService mealService;

    @Test
    public void test_saveAndLoadProduct() {
        // GIVEN
        Product product = new Product();
        product.setEnergy(311);
        product.setName("Hazel");
        product.setFat(99d);

        // WHEN
        productService.create(product);
        List<Product> products = productService.get();

        // THEN
        assertAll(
                () -> assertEquals(1, products.size()),
                () -> assertEquals("Hazel", products.get(0).getName())
        );
    }

    @Test
    @Transactional
    public void test_saveListOfMealsForOneProduct() {
        // GIVEN
        Product product = Product.builder().name("Water").energy(0).fat(0d).build();
        Product productCurrent = productService.create(product);

        // WHEN
        Meal mealOne = Meal.builder().dateTime(LocalDateTime.now()).portion(2d).product(productCurrent).build();
        Meal mealTwo = Meal.builder().dateTime(LocalDateTime.now()).portion(3d).product(productCurrent).build();
        Meal mealThree = Meal.builder().dateTime(LocalDateTime.now()).portion(24d).product(productCurrent).build();
        mealService.create(mealOne);
        mealService.create(mealTwo);
        mealService.create(mealThree);

        // THEN
        Integer id = productCurrent.getId();
        Product byId = productService.getById(id);
        Hibernate.initialize(byId.getMeals().size());
        assertEquals(byId.getMeals().size(), 3);
    }

}
