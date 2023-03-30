package edu.usc.csci310.project.loginsignup;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class AppConfig {
    @Value("jdbc:mysql://localhost:3306/db?createDatabaseIfNotExist=true")
    public String datasourceUrl;

    @Value("root")
    public String username;

    @Value("root")
    public String password;

    @Value("${spring.datasource.driver-class-name}")
    public String driverClassName;

    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(datasourceUrl);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }
}
