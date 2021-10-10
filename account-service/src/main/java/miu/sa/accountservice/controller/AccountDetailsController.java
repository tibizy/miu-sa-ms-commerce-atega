package miu.sa.accountservice.controller;

import miu.sa.accountservice.model.AccountDetails;
import miu.sa.accountservice.service.AccountDetailsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/account-details")
public class AccountDetailsController {

    private final AccountDetailsService service;

    public AccountDetailsController(AccountDetailsService service) {
        this.service = service;
    }

    @GetMapping("{id}")
    public ResponseEntity<AccountDetails> getAccountDetails(@PathVariable Long id){
        return ResponseEntity.ok(service.getCustomerDetails(id));
    }
}
