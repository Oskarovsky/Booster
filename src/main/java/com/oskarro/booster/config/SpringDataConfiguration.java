package com.oskarro.booster.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories("com.oskarro.booster.repository")
public class SpringDataConfiguration {

    /* SPRING DATA CONFIGURATION */
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DATABASE_TO_UPPER=false");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;
    }

    /**
     * Transaction manager bean based on an entity manager factory. Every interaction with the database
     * should occur within transaction boundaries and Spring Data needs a transaction manager bean.
     * */
    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }


    /**
     * JPA vendor adapter bean that is needed by JPA to interact with Hibernate
     * */
    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setDatabase(Database.H2);
        jpaVendorAdapter.setShowSql(true);
        return jpaVendorAdapter;
    }

    /**
     * Factory bean that produces an EntityManagerFactory following the JPA standard container bootstrap contract.
     * */
    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        localContainerEntityManagerFactoryBean.setDataSource(dataSource());
        Properties properties = new Properties();
        properties.put("hibernate.hbm2ddl.auto", "create");
        localContainerEntityManagerFactoryBean.setJpaProperties(properties);
        localContainerEntityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter());
        localContainerEntityManagerFactoryBean.setPackagesToScan("com.oskarro.booster");
        return localContainerEntityManagerFactoryBean;
    }


    /* HIBERNATE CONFIGURATION*/
    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("com.oskarro.booster.model");
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    @Bean
    public PlatformTransactionManager hibernateTransactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }

    private Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        hibernateProperties.setProperty("hibernate.connection.url", "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DATABASE_TO_UPPER=false");
        hibernateProperties.setProperty("hibernate.connection.username", "sa");
        hibernateProperties.setProperty("hibernate.connection.password", "");
        hibernateProperties.setProperty("hibernate.connection.pool_size", "3");
        hibernateProperties.setProperty("hibernate.current_session_context_class", "thread");
        return hibernateProperties;
    }
}