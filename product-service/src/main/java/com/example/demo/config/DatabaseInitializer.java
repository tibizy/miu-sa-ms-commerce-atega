package com.example.demo.config;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Slf4j
public class DatabaseInitializer implements ApplicationListener<ApplicationReadyEvent> {

    private final List<Product> products = List.of(
            new Product(null, "Ada Adam", "Ada Inc", 70L, 500.0, "Electronics"),
            new Product(null, "Simon Peter", "Simon Co.", 100L, 25.23, "Apparel"),
            new Product(null, "Pelligrini", "Ferragamo", 65L, 14.10, "Beauty"),
            new Product(null, "Mourinho Jose", "Hiky", 80L, 46.00, "Outdoor"),
            new Product(null, "Betty Ferguson", "Haley", 98L, 7.83, "Grocery")
    );


    private final ProductRepository repository;

    public DatabaseInitializer(ProductRepository repository) {
        this.repository = repository;
    }


    @Override
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        log.info("Database preloading...");
        products.forEach(repository::save);
        log.info("Database preloaded::::");
        System.out.println(repository.findAll());
    }
}
