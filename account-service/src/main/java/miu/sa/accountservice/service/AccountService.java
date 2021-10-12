package miu.sa.accountservice.service;

import miu.sa.accountservice.exceptions.ResourceNotFoundException;
import miu.sa.accountservice.model.AccountDto;
import miu.sa.accountservice.model.entity.Account;
import miu.sa.accountservice.repository.AccountRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {
    private final AccountRepository repository;

    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    public boolean auth(Account account){
        return repository.findByEmailAndPassword(account.getEmail(), account.getPassword())
                .isPresent();
    }

    public AccountDto save(Account account) {
        // TODO: 11/7/21 password encryption
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        account.setPassword(encoder.encode(account.getPassword()));
        account.setActive(true);
        Account acct = repository.save(account);
        return new AccountDto(acct.getId(), acct.getEmail(), acct.getFirstName(), acct.getLastName(), acct.isActive(), acct.getAddresses(), acct.getPayments());
    }

    public List<AccountDto> findAll() {
        return repository.findAll().stream().map(a -> new AccountDto(a.getId(), a.getEmail(), a.getFirstName(), a.getLastName(), a.isActive(), a.getAddresses(), a.getPayments()))
                .collect(Collectors.toList());
    }

    public AccountDto findById(Long id) {
        return repository.findById(id).map(a -> new AccountDto(a.getId(), a.getEmail(), a.getFirstName(), a.getLastName(), a.isActive(), a.getAddresses(), a.getPayments()))
                .orElseThrow(
                        () -> new ResourceNotFoundException("Account " + id + " not found")
                );
    }

    // TODO: 11/8/21 password encryption for update or create new endpoint to update password
    public AccountDto update(Long id, Account account) {
        Account acct = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found for this id :: " + id));
        acct.setFirstName(account.getFirstName());
        acct.setLastName(account.getLastName());
        acct.setEmail(account.getEmail());
        acct.setAddresses(account.getAddresses());
        acct.setPayments(account.getPayments());
        return save(acct);
    }

    public void delete(Long id) {
        Account account = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Account " + id + " not found")
        );
        repository.delete(account);
    }


    public AccountDto deactivate(Long id) {
        Account acct = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found for this id :: " + id));
        acct.setActive(false);
        return save(acct);
    }

    public AccountDto activate(Long id) {
        Account acct = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found for this id :: " + id));
        acct.setActive(true);
        return save(acct);
    }

    public Account findByEmail(String email) {
        return repository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("Account " + email + " not found")
        );
    }
}
