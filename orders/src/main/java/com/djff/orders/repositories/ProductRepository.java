package com.djff.orders.repositories;

import com.djff.orders.Entities.ProductModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface ProductRepository extends MongoRepository<ProductModel, UUID> {
}
