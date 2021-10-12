package edu.miu.sa.cardservice.controller;

import edu.miu.sa.cardservice.dto.BasicResponse;
import edu.miu.sa.cardservice.dto.PaymentDTO;
import edu.miu.sa.cardservice.services.CardPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/")
public class PaymentController {

    @Autowired
    CardPaymentService cardPaymentService;

    @RequestMapping(value = "/card/pay", method = RequestMethod.POST)
    @Async
    public ResponseEntity<BasicResponse> cardPayment(@RequestBody PaymentDTO request){
        BasicResponse response = cardPaymentService.pay(request);

        if(!response.isSuccessful){
            return new ResponseEntity<BasicResponse>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<BasicResponse>(response, HttpStatus.OK);
    }
}
