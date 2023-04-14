package com.example.transactional.config;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import com.example.transactional.repo.ProductRepo;
import com.example.transactional.service.ProductService;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@EnableAspectJAutoProxy
@EnableTransactionManagement
@ComponentScan("com")
@Configuration
public class DatabaseConfig {

    private final String url = "jdbc:mysql://root@127.0.0.1:3306/transactional";

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl(url);
        dataSource.setUsername("root");
        dataSource.setPassword("1111");
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }



    LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();

    @Bean
    public void setLoggerContext() {
        loggerContext.getLogger("org.springframework.jdbc.core.JdbcTemplate").setLevel(Level.INFO);
        loggerContext.getLogger("org.springframework.jdbc").setLevel(Level.INFO);

    }



}
