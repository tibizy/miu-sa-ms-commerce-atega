package edu.miu.sa.paymentservice.services;

import edu.miu.sa.paymentservice.dtos.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AccountService {
    private String path = "/api/account/";
    private String baseUrl = "http://ACCOUNT-SERVICE";
    @Autowired
    RestTemplate restTemplate = new RestTemplate();
    public Customer getCustomer(Long id){
//        baseUrl = baseUrl + "/api/account/" + id;
        System.out.println(baseUrl);
        Customer cust = restTemplate.getForObject(baseUrl + path + id, Customer.class);
        System.out.println(cust);
        if(cust != null){

        }

        return cust;
    }
}
