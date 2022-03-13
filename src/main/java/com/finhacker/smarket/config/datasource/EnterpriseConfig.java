package com.finhacker.smarket.config.datasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactoryEnterprise",
        transactionManagerRef = "transactionManagerEnterprise",
        basePackages = { "com.finhacker.smarket.domain.enterprise" }
    )
public class EnterpriseConfig {

    @Autowired
    @Qualifier("enterpriseDataSource")
    private DataSource enterpriseDataSource;

    @Autowired
    private JpaProperties jpaProperties;
    @Autowired
    private HibernateProperties hibernateProperties;

    private Map<String, Object> getVendorProperties() {
        return hibernateProperties.determineHibernateProperties(jpaProperties.getProperties(), new HibernateSettings());
    }

    @Primary
    @Bean(name = "entityManagerEnterprise")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactoryEnterprise(builder).getObject().createEntityManager();
    }

    @Primary
    @Bean(name = "entityManagerFactoryEnterprise")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryEnterprise(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(enterpriseDataSource)
                .packages("com.finhacker.smarket.domain.enterprise")
                .properties(getVendorProperties())
                .build();
    }

    @Primary
    @Bean(name = "transactionManagerEnterprise")
    public PlatformTransactionManager transactionManagerEnterprise(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactoryEnterprise(builder).getObject());
    }

}
