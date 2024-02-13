package es.nextdigital.bank.controller;

import es.nextdigital.bank.model.Account;
import es.nextdigital.bank.model.Movement;
import es.nextdigital.bank.model.WithdrawRequest;
import es.nextdigital.bank.service.BankService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bank/api/v1")
public class BankController {
    private final BankService bankService;

    // TODO remove method, introduced for testing purposes
    @GetMapping("/accounts/{accountId}")
    public Account findAccount(@PathVariable String accountId) {
        return bankService.findAccount(accountId);
    }

    @GetMapping("/accounts/{accountId}/movements")
    public List<Movement> getMovementsFromAccount(@PathVariable String accountId) {
        return bankService.getMovementsFromAccount(accountId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/cards/{cardId}/withdrawal")
    public void withdrawFromCard(@PathVariable String cardId, @RequestParam Integer entityCode, @RequestBody WithdrawRequest request) {
        bankService.withdrawFromCard(cardId, entityCode, request);
    }
}
