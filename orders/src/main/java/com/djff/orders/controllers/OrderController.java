package com.djff.orders.controllers;

import com.djff.orders.Entities.OrderModel;
import com.djff.orders.dot.request.OrderRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.djff.orders.services.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    final private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public String getOrders(){
        return "Welcome to order endpoint";
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Object> getCustomersOrders(@PathVariable String customerId){
        return ResponseEntity.ok(orderService.getCustomerOrders(customerId));
    }

    @PostMapping("/{customerId}")
    public ResponseEntity<String> createNewOrder(@RequestBody OrderRequest orderRequest, @PathVariable String customerId){
        // 1. Create new order with products
        // 2. Deduct requested products from stock
        // 3. Contact payment transaction
        // 4. Contact shipment if payment is successful
        // 5. if payment is not successful, cancel order and revert stock deductions.

        // 1.
        OrderModel order = orderService.createOrder(orderRequest);

        //2.
        orderService.deductProductQuantity(order);

        //3.
        var paymentResponse = orderService.makeOrderPayment(order);

        if(paymentResponse){
            orderService.updateOrderPaymentStatus(order);
            orderService.shipOrder(order);
            return ResponseEntity.ok("Order Created Successfully");
        } else{
            return ResponseEntity.ok("Failed to create order");
        }
    }
}
