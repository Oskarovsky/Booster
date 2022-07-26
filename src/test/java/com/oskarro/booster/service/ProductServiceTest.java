package com.oskarro.booster.service;

import com.oskarro.booster.config.SpringDataConfiguration;
import com.oskarro.booster.model.Product;
import com.oskarro.booster.repository.ProductRepository;
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
    private ProductRepository productRepository;

    @Test
    public void test_saveAndLoadProduct() {
        // GIVEN
        Product product = new Product();
        product.setEnergy(311);
        product.setName("Hazel");
        product.setFat(99d);

        // WHEN
        productRepository.save(product);
        List<Product> products = (List<Product>) productRepository.findAll();

        // THEN
        assertAll(
                () -> assertEquals(1, products.size()),
                () -> assertEquals("Hazel", products.get(0).getName())
        );


    }

}
