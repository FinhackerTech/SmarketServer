package dev.finhacker.smarket.config.datasource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/*
Configure multiple data source.
 */
@Configuration
public class DataSourceConfig {

    /*
    Data source for enterprises.
     */
    @Primary
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.enterprise")
    public DataSource enterpriseDataSource() {
        return DataSourceBuilder.create().build();
    }

    /*
    Data source for users.
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.user")
    public DataSource userDataSource() {
        return DataSourceBuilder.create().build();
    }

}
