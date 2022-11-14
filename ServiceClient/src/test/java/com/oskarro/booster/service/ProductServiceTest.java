package com.oskarro.booster.service;

import com.oskarro.booster.config.SpringDataConfiguration;
import com.oskarro.booster.model.Product;
import com.oskarro.booster.model.Provider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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

    @Autowired
    ProviderService providerService;

    @Test
    public void test_saveAndLoadProduct() {
        // GIVEN
        Product product = new Product();
        product.setEnergy(311);
        product.setName("Hazel");
        product.setFat(99d);

        // WHEN
        productService.save(product);
        List<Product> products = productService.get();

        // THEN
        assertAll(
                () -> assertEquals(1, products.size()),
                () -> assertEquals("Hazel", products.get(0).getName())
        );
    }

    @Test
    public void test_saveAndLoadProductFromProvider() {
        // GIVEN
        Provider providerOne = new Provider();
        providerOne.setId(1);
        providerOne.setName("Prov1");
        Provider providerTwo = new Provider();
        providerTwo.setId(2);
        providerTwo.setName("Prov2");
        providerService.save(providerOne);
        providerService.save(providerTwo);

        Product productOne = new Product();
        productOne.setName("ProdName_First");
        productOne.setProviderById(1);
        Product productTwo = new Product();
        productTwo.setName("ProdName_Second");
        productTwo.setProviderById(1);
        productService.save(productOne);
        productService.save(productTwo);

        // WHEN
        List<Product> productsResult = productService.getAllProductsByProviderId(1);

        // THEN
        assertEquals(2, productsResult.size());
        assertEquals("ProdName_First", productsResult.get(0).getName());
        assertEquals("ProdName_Second", productsResult.get(1).getName());
    }
}
