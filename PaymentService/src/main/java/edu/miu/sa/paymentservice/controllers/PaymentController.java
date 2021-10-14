package edu.miu.sa.paymentservice.controllers;

import edu.miu.sa.paymentservice.dtos.BasicResponse;
import edu.miu.sa.paymentservice.dtos.PaymentDTO;
import edu.miu.sa.paymentservice.services.AccountService;
import edu.miu.sa.paymentservice.services.ProcessTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/payment")
public class PaymentController {

    @Autowired
    private ProcessTransaction processTransaction;
    @Autowired
    AccountService service;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<BasicResponse> makePayment(@RequestBody PaymentDTO request){
        BasicResponse response = processTransaction.makePayment(request);

        if(!response.getSuccessful()){
            return new ResponseEntity<BasicResponse>(response, HttpStatus.BAD_REQUEST);

        }
        return new ResponseEntity<BasicResponse>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> test(){
        service.getCustomer(1L);
        return ResponseEntity.ok("fine");
    }
}
