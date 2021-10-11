package edu.miu.sa.paymentservice.processor;

import edu.miu.sa.paymentservice.dtos.BasicResponse;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BankService {
    private static final String baseURL = "";
    static RestTemplate restTemplate = new RestTemplate();

    public BasicResponse payByBank(Object request){
        BasicResponse response = new BasicResponse(false);
        response = restTemplate.postForObject(baseURL, request, BasicResponse.class);

        return response;

    }
}
