package edu.miu.sa.paymentservice.dtos;

import edu.miu.sa.paymentservice.model.PaymentStatus;
import edu.miu.sa.paymentservice.model.PaymentType;

import java.time.LocalDateTime;

public class PaymentDTO {
    public Long customerReference;
    public String orderNumber;
    public Double amount;
    public PaymentType type;
}

