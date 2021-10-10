package edu.miu.sa.cardservice.services;

import edu.miu.sa.cardservice.dto.BasicResponse;
import edu.miu.sa.cardservice.dto.PaymentDTO;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class CardPaymentService {

    @Async
    public BasicResponse pay(PaymentDTO request){
        BasicResponse response = new BasicResponse(false);
        if(request.getAmount() < 20){
            return response;
        }
        else{
            response.isSuccessful = true;
        }
        return response;
    }
}
