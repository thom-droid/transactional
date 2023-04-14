package com.example.transactional;

import com.example.transactional.aop.DataSourceAspect;
import com.example.transactional.config.DatabaseConfig;
import com.example.transactional.service.ProductService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.example.transactional"})
public class TransactionalApplication {

    public static void main(String[] args) {
//        SpringApplication.run(TransactionalApplication.class, args);

        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(DatabaseConfig.class);
        context.registerShutdownHook();

        ProductService productService = context.getBean("productService", ProductService.class);
        productService.saveProductInfo();

        context.close();
    }

}
