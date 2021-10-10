package edu.miu.sa.paymentservice.services;

import edu.miu.sa.paymentservice.dtos.BasicResponse;
import edu.miu.sa.paymentservice.dtos.PaymentDTO;
import edu.miu.sa.paymentservice.model.PaymentStatus;
import edu.miu.sa.paymentservice.model.Transaction;
import edu.miu.sa.paymentservice.repository.PaymentRepository;
import edu.miu.sa.paymentservice.utility.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransactionService {
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private Util utils;

    public BasicResponse addTransaction(PaymentDTO request){
        BasicResponse response = new BasicResponse(false);
        Transaction trans = new Transaction();
        trans.customerReference = request.customerReference;
        trans.paymentReference = utils.GenerateReference();
        trans.orderNumber = request.orderNumber;
        trans.amount = request.amount;
        trans.type = request.type;
        trans.status = PaymentStatus.PENDING;
        trans.requestTime = LocalDateTime.now();
        trans.dateCreated = LocalDateTime.now();

        var t = paymentRepository.save(trans);

        if(t == null){
            return response;
        }
        response.setSuccessful(true);
        return response;
    }

    public BasicResponse updateTransaction(Long paymentId){
        BasicResponse response = new BasicResponse(false);
        var trans = paymentRepository.findById(paymentId);

        if(trans != null){

        }

        //paymentRepository.
        return response;
    }
}
