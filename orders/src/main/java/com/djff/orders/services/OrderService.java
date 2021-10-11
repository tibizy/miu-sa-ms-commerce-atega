package com.djff.orders.services;

import com.djff.orders.Entities.OrderModel;
import com.djff.orders.Entities.ProductModel;
import dot.request.OrderRequest;
import dot.request.ProductRequest;
import org.springframework.stereotype.Service;
import com.djff.orders.repositories.OrderRepository;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderService {
    final private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
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
}
