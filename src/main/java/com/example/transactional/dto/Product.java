package com.example.transactional.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Product {

    private String name;

    public Product(String name) {
        this.name = name;
    }

}
