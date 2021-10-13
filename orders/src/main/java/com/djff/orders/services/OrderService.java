package com.djff.orders.services;

import com.djff.orders.Entities.OrderModel;
import com.djff.orders.Entities.ProductModel;
import com.djff.orders.dot.request.OrderRequest;
import com.djff.orders.dot.request.PaymentRequest;
import com.djff.orders.dot.request.ProductRequest;
import com.djff.orders.dot.response.PaymentResponse;
import com.djff.orders.dot.response.ShippingResponse;
import org.springframework.stereotype.Service;
import com.djff.orders.repositories.OrderRepository;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderService {
    final private OrderRepository orderRepository;
    final private RestTemplate restTemplate;

    public OrderService(OrderRepository orderRepository, RestTemplate restTemplate) {
        this.orderRepository = orderRepository;
        this.restTemplate = restTemplate;
    }

    public void updateOrderShippingStatus(OrderModel orderModel){
        orderModel.setShipped(true);
        orderRepository.save(orderModel);
    }
    public void updateOrderPaymentStatus(OrderModel orderModel){
        orderModel.setPaid(true);
        orderRepository.save(orderModel);
    }

    public List<ProductModel> getAllProducts(UUID orderId){
        var order = orderRepository.findById(orderId);
        return order.map(OrderModel::getProducts).orElse(null);
    }

    public Double getOrderAmount(OrderModel orderModel){
        return orderModel.getProducts().stream().mapToDouble(ProductModel::getPrice).sum();
    }

    public List<OrderModel> getCustomerOrders(String customerId){
        return orderRepository.findOrderModelByCustomerId(customerId);
    }

    public OrderModel createOrder(OrderRequest orderRequest){
        OrderModel orderModel = new OrderModel();

        orderModel.setCustomerId(orderRequest.getCustomerId());
        orderModel.setPaymentType(orderRequest.getPaymentType());
        List<ProductModel> products =
        orderRequest.getProductRequests().stream().map(this::createProductModel).collect(Collectors.toList());

        orderModel.setProducts(products);
        orderRepository.save(orderModel);

        return orderModel;
    }

    public ProductModel createProductModel(ProductRequest productRequest){
        ProductModel productModel = new ProductModel();
        productModel.setProductId(productRequest.getProductId());
        productModel.setQuantity(productRequest.getQuantity());
        productModel.setPrice(productRequest.getPrice());

        return productModel;
    }

    public boolean makeOrderPayment(OrderModel order){
        System.out.println("============= Making order Payment ==============");
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setOrderNumber(order.getOrderId());
        paymentRequest.setCustomerReference(order.getCustomerId());
        paymentRequest.setType(PaymentRequest.PaymentType.BANK);
        paymentRequest.setAmount(getOrderAmount(order));

        PaymentResponse paymentResponse =
                restTemplate.postForObject("http://payment-service/api/payment",
                        paymentRequest, PaymentResponse.class);
        return Objects.requireNonNull(paymentResponse).getIsSuccessful();
    }

    public void shipOrder(OrderModel order){
        System.out.println("============ Shipping Order paid products ==========");
        ShippingResponse shippingResponse =
                restTemplate.getForObject(
                        "http://shipping-service/api/shipping/"+order.getOrderId(), ShippingResponse.class);
        updateOrderShippingStatus(order);
    }

    public void deductProductQuantity(OrderModel orderModel){
        System.out.println("============= Deducting order products from inventory ============");
    }
}
