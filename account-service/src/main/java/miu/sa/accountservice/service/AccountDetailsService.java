package miu.sa.accountservice.service;

import lombok.extern.slf4j.Slf4j;
import miu.sa.accountservice.model.AccountDto;
import miu.sa.accountservice.model.entity.Address;
import miu.sa.accountservice.model.AccountDetails;
import miu.sa.accountservice.model.entity.PaymentMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
public class AccountDetailsService {

    private final RestTemplate restTemplate;
    private final AccountService service;

    public AccountDetailsService(RestTemplate restTemplate, AccountService service) {
        this.restTemplate = restTemplate;
        this.service = service;
    }


    public AccountDetails getCustomerDetails(Long id) {
        log.info("Inside CustomerDetailsService of ACCOUNT service");
        AccountDetails cd = new AccountDetails();

        AccountDto account = service.findById(id);


        List<Address> addresses  = (List<Address>) restTemplate.getForObject("http://shipping-service/address/" + id,
                Address.class);
        List<PaymentMethod> paymentMethods  = (List<PaymentMethod>) restTemplate.getForObject("http://payment-service/payment/" + id
                        , PaymentMethod.class);

        cd.setAccount(account);
        cd.setAddresses(addresses);
        cd.setPayments(paymentMethods);

        return cd;
    }
}
