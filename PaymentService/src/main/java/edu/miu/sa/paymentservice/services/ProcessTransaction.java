package edu.miu.sa.paymentservice.services;

import edu.miu.sa.paymentservice.dtos.BasicResponse;
import edu.miu.sa.paymentservice.dtos.Customer;
import edu.miu.sa.paymentservice.dtos.PaymentDTO;
import edu.miu.sa.paymentservice.processor.BankService;
import edu.miu.sa.paymentservice.processor.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        //get default payment method
        if(customer.getPayments().stream().distinct().){

        }


        switch(request.type){
            case CARD:

                response = cardService.payByCard();
                break;
            case BANK:
                response = bankService.payByBank(new Object());
                break;
            default:
                break;
        }

        //TO DO: update payment with response from transaction services
        if(!response.getSuccessful()){

        }


        transactionService.updateTransaction();
        return response;
    }
}
