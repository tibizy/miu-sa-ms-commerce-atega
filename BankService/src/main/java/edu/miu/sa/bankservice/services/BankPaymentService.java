package edu.miu.sa.bankservice.services;

import edu.miu.sa.bankservice.dto.BasicResponse;
import edu.miu.sa.bankservice.dto.PaymentDTO;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class BankPaymentService {
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
