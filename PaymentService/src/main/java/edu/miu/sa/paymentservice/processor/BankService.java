package edu.miu.sa.paymentservice.processor;

import edu.miu.sa.paymentservice.dtos.Bank;
import edu.miu.sa.paymentservice.dtos.BasicResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BankService {
    private static final String baseURL = "http://localhost:8790";
    static RestTemplate restTemplate = new RestTemplate();

    public BasicResponse payByBank(Bank request){
        BasicResponse response = new BasicResponse(false);
        response = restTemplate.postForObject(baseURL + "/api/bank/pay", request, BasicResponse.class);

        return response;

    }
}
