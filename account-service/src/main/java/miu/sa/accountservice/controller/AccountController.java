package miu.sa.accountservice.controller;

import miu.sa.accountservice.model.AccountDto;
import miu.sa.accountservice.model.entity.Account;
import miu.sa.accountservice.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/account")
public class AccountController {

    private final AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }

    @PostMapping("create")
    public ResponseEntity<AccountDto> addAccount(@RequestBody Account account) {
        return ResponseEntity.ok(service.save(account));
    }

    @GetMapping
    public ResponseEntity<List<AccountDto>> findAll() {
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
