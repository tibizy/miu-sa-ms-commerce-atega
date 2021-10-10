package miu.sa.accountservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import miu.sa.accountservice.enums.PaymentType;
import miu.sa.accountservice.model.entity.PaymentMethod;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditCardPayment {
    private String cardNumber;
    private String nameOnCard;
    private String expDate;
}
