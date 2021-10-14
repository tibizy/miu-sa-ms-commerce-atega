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
    public ResponseEntity<OrderModel> createNewOrder(@RequestBody OrderRequest orderRequest, @PathVariable Long customerId){
        // 1. Create new order with products
        // 2. Deduct requested products from stock
        // 3. Contact payment transaction
        // 4. Contact shipment if payment is successful
        // 5. if payment is not successful, cancel order and revert stock deductions.

        System.out.println("*************************");
        System.out.println(orderRequest.getPaymentType());
        System.out.println(orderRequest.getProductRequests().size());
        orderRequest.getProductRequests().forEach(
                p -> System.out.println(p.getProductId())
        );
        System.out.println(orderRequest.getCardNumber());
        // 1.
        orderRequest.setCustomerId(customerId);
        OrderModel order = orderService.createOrder(orderRequest);

        //2.
        orderService.deductProductQuantity(order);

        //3.
        var paymentResponse = orderService.makeOrderPayment(order, orderRequest);

        if(paymentResponse){
            orderService.updateOrderPaymentStatus(order);
            orderService.shipOrder(order);
            return ResponseEntity.ok(order);
        } else{
            return ResponseEntity.ok(new OrderModel());
        }
    }
}
