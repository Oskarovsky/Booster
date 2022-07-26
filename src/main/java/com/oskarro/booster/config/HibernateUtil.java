//package com.oskarro.booster.config;
//
//import com.oskarro.booster.model.Meal;
//import com.oskarro.booster.model.Product;
//import org.hibernate.SessionFactory;
//import org.hibernate.boot.Metadata;
//import org.hibernate.boot.MetadataSources;
//import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
//import org.hibernate.service.ServiceRegistry;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class HibernateUtil {
//
//    public static SessionFactory getSessionFactory() {
//
//        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
//                .applySettings(dbSettings())
//                .build();
//
//        Metadata metadata = new MetadataSources(serviceRegistry)
//                .addAnnotatedClass(Meal.class)
//                .addAnnotatedClass(Product.class)
//                // other domain classes
//                .buildMetadata();
//
//        return metadata.buildSessionFactory();
//    }
//
//    private static Map<String, String> dbSettings() {
//        Map<String, String> map = new HashMap<>();
//        map.put("hibernate.hbm2ddl.auto", "create-drop");
//        map.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
//        map.put("hibernate.connection.url", "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DATABASE_TO_UPPER=false");
//        map.put("hibernate.connection.username", "sa");
//        map.put("hibernate.connection.password", "");
//        map.put("hibernate.connection.pool_size", "3");
//        map.put("hibernate.current_session_context_class", "thread");
//        return map;
//    }
//}
