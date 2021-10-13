package edu.miu.sa.paymentservice.dtos;

import edu.miu.sa.paymentservice.model.PaymentStatus;
import edu.miu.sa.paymentservice.model.PaymentType;

import java.time.LocalDateTime;

public class PaymentDTO {
    private Long customerReference;
    private String orderNumber;
    private Double amount;
    private PaymentType type;
    private String cardNumber;
    private String nameOnCard;
    private String expDate;
    private String accountNo;
    private String routingNo;
    private String accountName;

    public Long getCustomerReference() {
        return customerReference;
    }

    public void setCustomerReference(Long customerReference) {
        this.customerReference = customerReference;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public PaymentType getType() {
        return type;
    }

    public void setType(PaymentType type) {
        this.type = type;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getNameOnCard() {
        return nameOnCard;
    }

    public void setNameOnCard(String nameOnCard) {
        this.nameOnCard = nameOnCard;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getRoutingNo() {
        return routingNo;
    }

    public void setRoutingNo(String routingNo) {
        this.routingNo = routingNo;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
}

