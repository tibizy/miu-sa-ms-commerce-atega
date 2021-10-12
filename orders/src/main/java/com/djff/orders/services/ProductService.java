package com.djff.orders.services;

import org.springframework.stereotype.Service;
import com.djff.orders.repositories.ProductRepository;

@Service
public class ProductService {
    final private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


}
