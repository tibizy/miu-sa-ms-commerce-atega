package com.djff.orders.controllers;

import com.djff.orders.Entities.OrderModel;
import com.djff.orders.dot.request.OrderRequest;
import com.djff.orders.dot.request.PaymentRequest;
import com.djff.orders.dot.response.PaymentResponse;
import com.djff.orders.dot.response.ShippingResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.djff.orders.services.OrderService;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/orders")
public class OrderController {

    final private OrderService orderService;
    final private RestTemplate restTemplate;

    public OrderController(OrderService orderService, RestTemplate restTemplate) {
        this.orderService = orderService;
        this.restTemplate = restTemplate;
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
    public ResponseEntity<Object> createNewOrder(@RequestBody OrderRequest orderRequest, @PathVariable String customerId){
        // 1. Create new order with products
        // 2. Deduct requested products from stock
        // 3. Contact payment transaction
        // 4. Contact shipment if payment is successful
        // 5. if payment is not successful, cancel order and revert stock deductions.

        // 1.
        OrderModel order = orderService.createOrder(orderRequest);

        //2.


        //3.
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setOrderNumber(order.getOrderId());
        paymentRequest.setCustomerReference(order.getCustomerId());
        paymentRequest.setType(PaymentRequest.PaymentType.BANK);
        paymentRequest.setAmount(orderService.getOrderAmount(order));

        PaymentResponse paymentResponse = restTemplate.postForObject("http://payment-service/payment", paymentRequest, PaymentResponse.class);

        if(paymentResponse != null &&paymentResponse.getIsSuccessful()){
            orderService.updateOrderPaymentStatus(order);
            //4.
            ShippingResponse shippingResponse =
                    restTemplate.getForObject("http://shipping-service/api/shipping/"+order.getOrderId(), ShippingResponse.class);
            if(shippingResponse != null){
                orderService.updateOrderShippingStatus(order);
            }
        } else{
            // 5.
            // TODO: revert the product deduction from inventory
        }
        return ResponseEntity.ok(new Object());
    }
}
