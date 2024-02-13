package es.nextdigital.bank.service;

import es.nextdigital.bank.exception.WithdrawalException;
import es.nextdigital.bank.model.*;
import es.nextdigital.bank.repository.AccountRepository;
import es.nextdigital.bank.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BankServiceImpl implements BankService {
    private final CardRepository cardRepository;
    private final AccountRepository accountRepository;
    private final Map<Integer, BankEntity> externalEntitis = Map.of(
            4120, BankEntity.builder().name("SABADELL").comission(8.0).build(),
            1110, BankEntity.builder().name("CAIXABANK").comission(15.0).build(),
            1320, BankEntity.builder().name("CAJAMAR").comission(13.0).build());

    @Override
    public Account findAccount(String accountId) {
        return accountRepository.findById(accountId);
    }

    @Override
    public List<Movement> getMovementsFromAccount(String accountId) {
        return accountRepository.findById(accountId).getMovements();
    }

    @Override
    public void withdrawFromCard(String cardId, Integer entityCode, WithdrawRequest request) {
        var card = cardRepository.findById(cardId);
        var amount = request.getAmount();
        amount += this.getWithdrawalCommission(entityCode);
        switch (card) {
            case CreditCard creditCard -> withdrawFromCreditCard(creditCard, amount);
            case DebitCard debitCard -> withdrawFromDebitCard(debitCard, amount);
        }
    }

    private double getWithdrawalCommission(Integer entityCode) {
        if (this.isExternalEntity(entityCode)) {
            return externalEntitis.get(entityCode).getComission();
        }
        return 0;
    }

    private boolean isExternalEntity(Integer entityCode) {
        return externalEntitis.containsKey(entityCode);
    }

    private void withdrawFromCreditCard(CreditCard creditCard, double amount) {
        var availableCredit = creditCard.getAvailableCredit();
        if (availableCredit - amount >= 0) {
            creditCard.setAvailableCredit(availableCredit - amount);
        } else {
            throw new WithdrawalException("Credit limit exceeded");
        }
    }

    private void withdrawFromDebitCard(DebitCard debitCard, double amount) {
        var dailyLimit = debitCard.getDailyLimit();
        var account = accountRepository.findById(debitCard.getAccount());
        if (account.getBalance() - amount >= 0 && dailyLimit - amount >= 0) {
            debitCard.setDailyLimit(dailyLimit - amount);
            account.setBalance(account.getBalance() - amount);
        } else {
            throw new WithdrawalException("Credit limit exceeded");
        }
    }
}
