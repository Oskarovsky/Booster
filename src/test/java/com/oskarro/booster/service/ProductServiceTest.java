package com.oskarro.booster.service;

import com.oskarro.booster.model.Product;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductServiceTest {

    @Test
    public void whenApplicationStarts_thenHibernateCreatesData() {

    }

    @Test
    public void test_storeProduct() {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("boosterPersistenceUnit");

        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            Product product = new Product();
            product.setName("Tomato");
            product.setEnergy(30);
            entityManager.persist(product);
            entityManager.getTransaction().commit();

            entityManager.getTransaction().begin();
            List<Product> products = entityManager.createQuery("SELECT p from Product p").getResultList();

            products.get(products.size() -1).setEnergy(45);
            entityManager.getTransaction().commit();

            assertAll(
                    () -> assertEquals(1, products.size()),
                    () -> assertEquals(45, products.get(0).getEnergy())
            );

            entityManager.close();

        } finally {
            entityManagerFactory.close();
        }
    }
}
