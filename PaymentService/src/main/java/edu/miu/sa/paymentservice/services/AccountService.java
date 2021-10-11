package edu.miu.sa.paymentservice.services;

import edu.miu.sa.paymentservice.dtos.Customer;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AccountService {

    private static String baseUrl = "";
    RestTemplate restTemplate = new RestTemplate();
    public Customer getCustomer(Long id){
        baseUrl = baseUrl + "/" + id;
        Customer cust = restTemplate.getForObject(baseUrl, Customer.class);

        if(cust != null){

        }

        return cust;
    }
}
