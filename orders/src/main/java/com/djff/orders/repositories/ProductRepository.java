package com.djff.orders.repositories;

import com.djff.orders.Entities.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<ProductModel, Long> {
}
