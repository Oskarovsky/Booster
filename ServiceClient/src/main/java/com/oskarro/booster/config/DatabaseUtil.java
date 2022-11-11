package com.oskarro.booster.config;

import com.oskarro.booster.model.Meal;
import com.oskarro.booster.model.Product;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class DatabaseUtil {

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
        return Persistence.createEntityManagerFactory("boosterPersistenceUnit", properties);
    }

}