package com.example.transactional.repo;

import com.example.transactional.dto.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ProductRepo {

    private final JdbcTemplate jdbcTemplate;

    public void saveProduct(Product product) {

        String sql = "INSERT INTO PRODUCT (name) VALUES (?)";

        Object[] args = {product.getName()};

        jdbcTemplate.update(sql, args);

    }
}
