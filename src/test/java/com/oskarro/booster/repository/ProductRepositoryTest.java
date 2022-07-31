package com.oskarro.booster.repository;

import com.oskarro.booster.model.Product;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    ProductRepository productRepository;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void test_saveProduct() {
        // GIVEN
        Product product = entityManager.persist(Product.builder().name("ProductName").fat(10d).energy(250).protein(2d).build());

        // WHEN
        productRepository.save(product);

        // THEN
        assertThat(product.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void test_getProduct() {
        // GIVEN
        Product product = productRepository.findById(1).get();

        // THEN
        assertThat(product.getId()).isEqualTo(1);
    }

    @Test
    @Order(3)
    public void test_getListOfProducts() {
        // WHEN
        List<Product> products = productRepository.findAll();

        // THEN
        assertThat(products.size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    public void test_updateProduct() {
        // GIVEN
        Product product = productRepository.findById(1).get();

        // WHEN
        product.setName("UpdatedName");
        Product currentProduct = productRepository.save(product);

        // THEN
        assertThat(currentProduct.getName()).isEqualTo("UpdatedName");
    }

    @Test
    @Order(5)
    @Rollback(value = false)
    public void test_deleteProduct() {
        // GIVEN
        Product product = productRepository.findById(1).get();
        assertThat(product).isNotNull();

        // WHEN
        productRepository.delete(product);
        Optional<Product> optionalProduct = productRepository.findById(1);

        Product productResult = null;
        if (optionalProduct.isPresent()) {
            productResult = optionalProduct.get();
        }

        // THEN
        assertThat(productResult).isNull();
    }
}
