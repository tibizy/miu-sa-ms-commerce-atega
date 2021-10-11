package edu.miu.sa.paymentservice.controllers;

import edu.miu.sa.paymentservice.dtos.BasicResponse;
import edu.miu.sa.paymentservice.dtos.PaymentDTO;
import edu.miu.sa.paymentservice.services.ProcessTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/payment")
public class PaymentController {

    @Autowired
    private ProcessTransaction ProcessTransaction;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<BasicResponse> makePayment(@RequestBody PaymentDTO request){
        BasicResponse response = ProcessTransaction.makePayment(request);

        if(!response.getSuccessful()){
            return new ResponseEntity<BasicResponse>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<BasicResponse>(HttpStatus.OK);
    }
}
