package es.nextdigital.bank.controller;

import es.nextdigital.bank.model.Movement;
import es.nextdigital.bank.service.BankService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bank/api/v1")
public class BankController {
    private final BankService bankService;

    @GetMapping("/accounts/{accountId}/movements")
    public List<Movement> findMovements(@PathVariable String accountId) {
        return bankService.findMovements(accountId);
    }
}
