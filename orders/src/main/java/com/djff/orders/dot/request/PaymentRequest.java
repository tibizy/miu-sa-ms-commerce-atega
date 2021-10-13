package com.djff.orders.dot.request;

import java.util.UUID;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {
    public enum PaymentType {
        CARD, BANK
    }

    public Long customerReference;
    public Long orderNumber;
    public Double amount;
    public PaymentType type;
    private String cardNumber;
    private String nameOnCard = null;
    private String expDate = null;
    private String accountNo = null;
    private String routingNo = null;
    private String accountName = null;
}
