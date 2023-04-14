package com.example.transactional.service;

import com.example.transactional.dto.Product;
import com.example.transactional.repo.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepo productRepo;

    @Transactional
    public void saveProductInfo() {

        for (int i = 1; i <= 10; i++) {

            Product product = new Product("food ");
            productRepo.saveProduct(product);

        }

    }

}
