package com.oskarro.booster;

import com.oskarro.booster.model.Meal;
import com.oskarro.booster.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import java.time.LocalDateTime;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class HibernateTest {

    private static SessionFactory createSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.configure().addAnnotatedClass(Meal.class);
        configuration.configure().addAnnotatedClass(Product.class);
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    private static SessionFactory getSessionFactory(EntityManagerFactory entityManagerFactory) {
        return entityManagerFactory.unwrap(SessionFactory.class);
    }

    private static EntityManagerFactory createEntityManagerFactory() {
        Configuration configuration = new Configuration();
        configuration.configure()
                .addAnnotatedClass(Product.class)
                .addAnnotatedClass(Meal.class);

        Map<String, String> properties = new HashMap<>();
        Enumeration<?> propertyNames = configuration.getProperties().propertyNames();
        while (propertyNames.hasMoreElements()) {
            String element = (String) propertyNames.nextElement();
            properties.put(element, configuration.getProperties().getProperty(element));
        }
        return Persistence.createEntityManagerFactory("boosterPersistenceUnitTest", properties);
    }

    @Test
    public void whenApplicationStarts_thenHibernateCreatesData() {

    }

    @Test
    public void test_storeLoadProduct_WithEntityManager() {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("boosterPersistenceUnitTest");

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

            products.get(products.size() - 1).setEnergy(45);
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

    @Test
    public void test_storeLoadMealAndProduct_WithSessionFactory() {
        try (SessionFactory sessionFactory = createSessionFactory();
             Session session = sessionFactory.openSession()) {

            // GI
            session.beginTransaction();
            Product product = new Product();
            product.setEnergy(99);
            session.persist(product);
            session.getTransaction().commit();

            CriteriaQuery<Product> criteriaQueryProduct = session.getCriteriaBuilder().createQuery(Product.class);
            criteriaQueryProduct.from(Product.class);

            List<Product> products = session.createQuery(criteriaQueryProduct).getResultList();

            assertAll(
                    () -> assertEquals(1, products.size()),
                    () -> assertEquals(99, products.get(0).getEnergy())
            );

            session.beginTransaction();
            Meal meal = new Meal();
            meal.setDateTime(LocalDateTime.now());
            meal.setPortion(2d);
            meal.setProduct(product);
            session.persist(meal);
            session.getTransaction().commit();

            CriteriaQuery<Meal> criteriaQueryMeal = session.getCriteriaBuilder().createQuery(Meal.class);
            criteriaQueryMeal.from(Meal.class);

            List<Meal> meals = session.createQuery(criteriaQueryMeal).getResultList();

            assertAll(
                    () -> assertEquals(1, meals.size()),
                    () -> assertEquals(2d, meals.get(0).getPortion()),
                    () -> assertNotNull(meals.get(0).getProduct())
            );
        }
    }
}
