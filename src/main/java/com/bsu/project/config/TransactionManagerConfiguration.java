package com.bsu.project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * @author Gulshirin Berdiyeva
 */
@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
public class TransactionManagerConfiguration {
    private final EntityManagerFactory entityManagerFactory;
    private final DataSource dataSource;

    @Autowired
    public TransactionManagerConfiguration(EntityManagerFactory entityManagerFactory, DataSource dataSource) {
        this.entityManagerFactory = entityManagerFactory;
        this.dataSource = dataSource;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();

        transactionManager.setEntityManagerFactory(entityManagerFactory);
        transactionManager.setDataSource(dataSource);

        return transactionManager;
    }

}
