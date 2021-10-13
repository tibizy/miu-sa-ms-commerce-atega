package com.djff.orders.repositories;

import com.djff.orders.Entities.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<OrderModel, Long> {
    List<OrderModel> findOrderModelByCustomerId(String customerId);
}
