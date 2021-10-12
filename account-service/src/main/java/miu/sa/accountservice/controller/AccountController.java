package miu.sa.accountservice.controller;

import lombok.extern.slf4j.Slf4j;
import miu.sa.accountservice.model.AccountDto;
import miu.sa.accountservice.model.entity.Account;
import miu.sa.accountservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/account")
@RefreshScope
@Slf4j
public class AccountController {

    @Value("${welcome.message:default welcome}")
    String message;
    private final AccountService service;
    private final Environment env;

    public AccountController(AccountService service, Environment env) {
        this.service = service;
        this.env = env;
    }

    @GetMapping("test-config")
    public ResponseEntity<?> testConfig(){
//        env.getProperty("welcome.message:default welcome")
        return ResponseEntity.ok(message);
    }

    //getAccount by Email
    @PostMapping("load")
    public ResponseEntity<Account> findAccountByEmail(@RequestBody AccountDto accountDto) {
        return ResponseEntity.ok().body(service.findByEmail(accountDto.getEmail()));
    }

    @PostMapping("create")
    public ResponseEntity<AccountDto> addAccount(@RequestBody Account account) {
        return ResponseEntity.ok(service.save(account));
    }

    @GetMapping
    public ResponseEntity<List<AccountDto>> findAll() {
        log.info("findAll controller");
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<AccountDto> findAccountById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<AccountDto> updateAccount(@PathVariable(value = "id") Long id,
                                                    @RequestBody Account account) {
        return ResponseEntity.ok(service.update(id, account));

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("deactivate/{id}")
    public ResponseEntity<AccountDto> deactivateAccount(@PathVariable Long id) {
        return ResponseEntity.ok(service.deactivate(id));
    }

    @PutMapping("activate/{id}")
    public ResponseEntity<AccountDto> activateAccount(@PathVariable Long id) {
        return ResponseEntity.ok(service.activate(id));
    }

}
