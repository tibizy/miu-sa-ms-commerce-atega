package com.djff.orders.services;

import com.djff.orders.Entities.OrderModel;
import com.djff.orders.Entities.ProductModel;
import com.djff.orders.dot.request.OrderRequest;
import com.djff.orders.dot.request.PaymentRequest;
import com.djff.orders.dot.request.ProductRequest;
import com.djff.orders.dot.request.ProductUpdateRequest;
import com.djff.orders.dot.response.PaymentResponse;
import com.djff.orders.dot.response.ShippingResponse;
import com.djff.orders.repositories.ProductRepository;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import com.djff.orders.repositories.OrderRepository;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class OrderService {
    final private OrderRepository orderRepository;
    final private ProductRepository productRepository;

    @LoadBalanced
    final private RestTemplate restTemplate;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository, RestTemplate restTemplate) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
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

    public List<ProductModel> getAllProducts(Long orderId){
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

        productRepository.saveAll(products);
        orderModel.setProducts(products);
        orderRepository.save(orderModel);

        return orderModel;
    }

    public ProductModel createProductModel(ProductRequest productRequest){
        ProductModel productModel = new ProductModel();
        productModel.setPrid(productRequest.getProductId());
        productModel.setQuantity(productRequest.getQuantity());
        productModel.setPrice(productRequest.getPrice());

        return productModel;
    }

    public boolean makeOrderPayment(OrderModel order, OrderRequest orderRequest){
        System.out.println("============= Making order Payment ==============");
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setOrderNumber(order.getOrderId());
        paymentRequest.setCustomerReference(order.getCustomerId());
        if(order.getPaymentType().equals("b"))paymentRequest.setType(PaymentRequest.PaymentType.BANK);
        else paymentRequest.setType(PaymentRequest.PaymentType.CARD);

        paymentRequest.setCardNumber(orderRequest.getCardNumber());
        paymentRequest.setNameOnCard(orderRequest.getNameOnCard());
        paymentRequest.setExpDate(orderRequest.getExpDate());
        paymentRequest.setAccountNo(orderRequest.getAccountNo());
        paymentRequest.setRoutingNo(orderRequest.getRoutingNo());
        paymentRequest.setAccountName(orderRequest.getAccountName());
        paymentRequest.setAmount(getOrderAmount(order));

//        PaymentResponse paymentResponse =
//                restTemplate.postForObject("http://payment-service/api/payment",
//                        paymentRequest, PaymentResponse.class);
        return true; //Objects.requireNonNull(paymentResponse).getIsSuccessful();
    }

    public void shipOrder(OrderModel order){
        System.out.println("============ Shipping Order paid products ==========");
        ShippingResponse shippingResponse =
                restTemplate.getForObject(
                        "lb://shipping-service/api/shipping/"+order.getOrderId(), ShippingResponse.class);
        updateOrderShippingStatus(order);
    }

    public void deductProductQuantity(OrderModel orderModel){
        System.out.println("============= Deducting order products from inventory ============");
        List<ProductUpdateRequest.ProductUpdateObject> products =
                orderModel.getProducts()
                        .stream()
                        .map(p -> new ProductUpdateRequest.ProductUpdateObject(p.getPrid(), p.getQuantity()))
                        .collect(Collectors.toList());
        restTemplate.put("lb://product-service/api/v1/products", new ProductUpdateRequest(products));
    }
}
