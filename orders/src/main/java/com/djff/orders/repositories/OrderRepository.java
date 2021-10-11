package com.djff.orders.repositories;

import com.djff.orders.Entities.OrderModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends MongoRepository<OrderModel, UUID> {
    List<OrderModel> findOrderModelByCustomerId(String customerId);
}
