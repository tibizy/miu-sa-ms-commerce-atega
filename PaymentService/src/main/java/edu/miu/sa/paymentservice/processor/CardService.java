package edu.miu.sa.paymentservice.processor;

import edu.miu.sa.paymentservice.dtos.BasicResponse;
import edu.miu.sa.paymentservice.dtos.Card;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CardService {
    private static final String baseURL = "http://localhost:8080";
    static RestTemplate restTemplate = new RestTemplate();

    public BasicResponse payByCard(Card request){
        BasicResponse response = new BasicResponse(false);
        response = restTemplate.postForObject(baseURL + "/api/card/pay", request, BasicResponse.class);

        return response;

    }
}
