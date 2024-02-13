package es.nextdigital.bank.service;

import es.nextdigital.bank.model.Account;
import es.nextdigital.bank.model.Movement;
import es.nextdigital.bank.model.WithdrawRequest;

import java.util.List;

public interface BankService {
    Account findAccount(String accountId);
    List<Movement> getMovementsFromAccount(String accountId);

    void withdrawFromCard(String cardId, Integer entityCode, WithdrawRequest request);

}
