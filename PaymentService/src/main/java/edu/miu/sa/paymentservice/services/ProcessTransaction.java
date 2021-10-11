package edu.miu.sa.paymentservice.services;

import edu.miu.sa.paymentservice.dtos.*;
import edu.miu.sa.paymentservice.processor.BankService;
import edu.miu.sa.paymentservice.processor.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;


@Service
public class ProcessTransaction {
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private BankService bankService;
    @Autowired
    private CardService cardService;
    @Autowired
    private AccountService accountService;

    public BasicResponse makePayment(PaymentDTO request){
        BasicResponse response = new BasicResponse(false);
        transactionService.addTransaction(request);

        Customer customer = accountService.getCustomer(request.customerReference);
        var customerPayments = customer.getPayments().stream().filter(x -> x.getDefault())
                .collect(Collectors.toList());

        Card cardRequest = new Card();
        Bank bankRequest = new Bank();
        for (var payment : customerPayments) {
            if(payment.getDefault() && (payment.getType() == "CC")){
                cardRequest.setCardNumber(payment.getMetaData().getCardNumber());
                cardRequest.setNameOnCard(payment.getMetaData().getNameOnCard());
                cardRequest.setExpDate(payment.getMetaData().getExpDate());
            }
            else if(payment.getDefault() && (payment.getType() == "BANK")){
                bankRequest.setAccountNo(payment.getMetaData().getAccountNo());
                bankRequest.setRoutingNo(payment.getMetaData().getRoutingNo());
                bankRequest.setAccountName(payment.getMetaData().getAccountName());
            }
        }

        switch(request.type){
            case CARD:
                response = cardService.payByCard(cardRequest);
                break;
            case BANK:
                response = bankService.payByBank(bankRequest);
                break;
            default:
                break;
        }

        //TO DO: update payment with response from transaction services
        if(!response.getSuccessful()){
            return response;
        }


        //transactionService.updateTransaction();
        return response;
    }
}
