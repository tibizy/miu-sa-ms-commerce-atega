package miu.sa.accountservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import miu.sa.accountservice.model.entity.Address;
import miu.sa.accountservice.model.entity.PaymentMethod;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDetails {
    private AccountDto account;
    private List<Address> addresses;
    private List<PaymentMethod> payments;
}
