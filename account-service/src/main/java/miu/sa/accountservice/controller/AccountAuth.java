package miu.sa.accountservice.controller;

import miu.sa.accountservice.model.entity.Account;
import miu.sa.accountservice.service.AccountService;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/account/auth")
public class AccountAuth {

    private final AccountService service;

    public AccountAuth(AccountService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> authenticate(@RequestBody Account account){
        boolean suc = service.auth(account);
        Map<String, Object> data = new HashMap<>();
        if(suc) {
            data.put("status", HttpStatus.OK);
            data.put("message", "Authentication Successful");
            data.put("data", new JSONObject());
            return ResponseEntity.ok().body(data);
        }
        else{

            data.put("status", HttpStatus.UNAUTHORIZED);
            data.put("message", "Authentication Failure");
            data.put("data", new JSONObject());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(data);
        }
    }
}
