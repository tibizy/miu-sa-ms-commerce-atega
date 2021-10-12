package edu.miu.sa.paymentservice.processor;

import edu.miu.sa.paymentservice.dtos.Bank;
import edu.miu.sa.paymentservice.dtos.BasicResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RefreshScope
public class BankService {
    @Value("${PAYMENT.BANK:http://BANK-SERVICE}")
    String baseUrl;

    private final String path = "/api/bank/pay";
    static RestTemplate restTemplate = new RestTemplate();

    public BasicResponse payByBank(Bank request){
        System.out.println(baseUrl);
        BasicResponse response = new BasicResponse(false);
        response = restTemplate.postForObject(baseUrl + path, request, BasicResponse.class);

        return response;

    }
}
