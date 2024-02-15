package es.nextdigital.account.controller;

import es.nextdigital.account.model.Account;
import es.nextdigital.account.model.Movement;
import es.nextdigital.account.service.AccountService;
import es.nextdigital.commons.model.Customer;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bank/api/v1/accounts")
public class AccountController {
    private final AccountService accountService;

    @GetMapping
    public List<Account> getAllAccounts(@RequestParam String documentNumber) {
        return accountService.findAll();
    }

//    @GetMapping("")
//    public List<Account> findCustomerAccounts(@RequestParam String documentNumber) {
//        return accountService.findCustomerAccounts(documentNumber);
//    }

    @GetMapping("/{iban}")
    public Account getAccountDetail(@PathVariable String iban) {
        return accountService.getAccountDetail(iban);
    }

    @GetMapping("/{iban}/movements")
    public List<Movement> getMovementsFromAccount(@PathVariable String iban) {
        return accountService.getMovementsFromAccount(iban);
    }
}
