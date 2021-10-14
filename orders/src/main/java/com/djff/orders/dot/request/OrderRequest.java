package com.djff.orders.dot.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter@Setter
@AllArgsConstructor
public class OrderRequest {
    private Long customerId;
    private String paymentType;
    private List<ProductRequest> productRequests;
    private String cardNumber;
    private String nameOnCard;
    private String expDate;
    private String accountNo;
    private String routingNo;
    private String accountName;


    public OrderRequest(){}
    public OrderRequest(String paymentType, List<ProductRequest> productRequests) {
        this.paymentType = paymentType;
        this.productRequests = productRequests;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public List<ProductRequest> getProductRequests() {
        return productRequests;
    }

    public void setProductRequests(List<ProductRequest> productRequests) {
        this.productRequests = productRequests;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }


}
