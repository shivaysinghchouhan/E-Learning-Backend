package com.microservices.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@Configuration
public class TransactionManagerConfig {

    @Bean(name = "postgresTransactionManager")
    public HibernateTransactionManager postgresTransactionManager(
            @Qualifier("postgresSessionFactory") LocalSessionFactoryBean sessionFactory) {
        return new HibernateTransactionManager(sessionFactory.getObject());
    }

    @Bean(name = "mysqlTransactionManager")
    public HibernateTransactionManager mysqlTransactionManager(
            @Qualifier("mysqlSessionFactory") LocalSessionFactoryBean sessionFactory) {
        return new HibernateTransactionManager(sessionFactory.getObject());
    }
}

