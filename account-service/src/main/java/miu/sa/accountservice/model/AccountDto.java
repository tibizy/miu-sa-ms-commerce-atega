package miu.sa.accountservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import miu.sa.accountservice.model.entity.Address;
import miu.sa.accountservice.model.entity.PaymentMethod;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private boolean isActive;
    private List<Address> addresses =  new ArrayList<>();
    private List<PaymentMethod> payments = new ArrayList<>();
}
