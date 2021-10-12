package miu.sa.accountservice.config;

import lombok.extern.slf4j.Slf4j;
import miu.sa.accountservice.enums.PaymentType;
import miu.sa.accountservice.model.BankPayment;
import miu.sa.accountservice.model.CreditCardPayment;
import miu.sa.accountservice.model.entity.Account;
import miu.sa.accountservice.model.entity.Address;
import miu.sa.accountservice.model.entity.PaymentMethod;
import miu.sa.accountservice.repository.AccountRepository;
import miu.sa.accountservice.service.AccountService;
import org.json.JSONObject;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Slf4j
public class DatabaseInitializer implements ApplicationListener<ApplicationReadyEvent> {

    private final List<Address> addresses = List.of(
            new Address(null, "1000 N 4th st", "52557", "Fairfield", "IA", true),
            new Address(null, "1000 N 4th st", "52557", "Fairfield", "IA", false)
    );

    private final List<Address> addresses2 = List.of(
            new Address(null, "1000 N 4th st", "52557", "Fairfield", "IA", true),
            new Address(null, "1000 N 4th st", "52557", "Fairfield", "IA", false)
    );
    private final List<Address> addresses3 = List.of(
            new Address(null, "1000 N 4th st", "52557", "Fairfield", "IA", true),
            new Address(null, "1000 N 4th st", "52557", "Fairfield", "IA", false)
    );

    private final PaymentMethod p1 = new PaymentMethod(null, PaymentType.CC, new JSONObject(new CreditCardPayment("1234", "ada lovelace", "04/24")).toString(), true);
    private final PaymentMethod p2 = new PaymentMethod(null, PaymentType.CC, new JSONObject(new CreditCardPayment("54678", "alan turing", "07/22")).toString(), true);
    private final PaymentMethod p3 = new PaymentMethod(null, PaymentType.BANK, new JSONObject(new BankPayment("9012345", "098765", "dennis ritchie")).toString(), true);

    private final List<Account> accounts = List.of(
            new Account(null, "Ada", "Lovelace", "ada.lovelace@miu.sa", "ada", true, addresses, List.of(p1)),
            new Account(null, "Alan", "Turing", "alan.turing@miu.sa", "alan", true, addresses2, List.of(p2)),
            new Account(null, "Dennis", "Ritchie", "dennis.ritchie@miu.sa", "dennis", true, addresses3, List.of(p3))
    );


    private final AccountService service;
    private final AccountRepository repository;

    public DatabaseInitializer(AccountService service, AccountRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        log.info("Database preloading...");
        accounts.forEach(service::save);
        log.info("Database preloaded::::");
        System.out.println(repository.findAll());
    }
}
