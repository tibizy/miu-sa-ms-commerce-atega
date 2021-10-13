package edu.miu.sa.paymentservice.processor;

import edu.miu.sa.paymentservice.dtos.BasicResponse;
import edu.miu.sa.paymentservice.dtos.Card;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RefreshScope
public class CardService {
    @Value("${PAYMENT.CARD:http://BANK-SERVICE}")
    String baseUrl;
    @Value("${services.token}")
    private String accountServiceToken;

    private final String path = "/api/card/pay";
    static RestTemplate restTemplate = new RestTemplate();

    public BasicResponse payByCard(Card request){
        System.out.println(baseUrl);
        BasicResponse response = new BasicResponse(false);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth(accountServiceToken);

        HttpEntity<Card> entity = new HttpEntity<>(request, headers);
        //response = restTemplate.postForObject(baseUrl + path, request, BasicResponse.class);
        response = restTemplate.postForObject(baseUrl + path, entity, BasicResponse.class);

        return response;

    }
}
