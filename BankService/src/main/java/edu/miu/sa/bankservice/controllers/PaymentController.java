package edu.miu.sa.bankservice.controllers;

import edu.miu.sa.bankservice.dto.BasicResponse;
import edu.miu.sa.bankservice.dto.PaymentDTO;
import edu.miu.sa.bankservice.services.BankPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {
    @Autowired
    BankPaymentService bankPaymentService;

    @RequestMapping(value = "/bank/pay", method = RequestMethod.GET)
    @Async
    public ResponseEntity<BasicResponse> cardPayment(@RequestBody PaymentDTO request){
        BasicResponse response = bankPaymentService.pay(request);

        if(!response.isSuccessful){
            return new ResponseEntity<BasicResponse>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<BasicResponse>(response, HttpStatus.OK);
    }
}
