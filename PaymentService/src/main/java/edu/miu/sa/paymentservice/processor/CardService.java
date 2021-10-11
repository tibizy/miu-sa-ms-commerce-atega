package edu.miu.sa.paymentservice.processor;

import edu.miu.sa.paymentservice.dtos.BasicResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CardService {
    private static final String baseURL = "";
    static RestTemplate restTemplate = new RestTemplate();

    public BasicResponse payByCard(Object request){
        BasicResponse response = new BasicResponse(false);
        response = restTemplate.postForObject(baseURL, request, BasicResponse.class);

        return response;

    }
}
