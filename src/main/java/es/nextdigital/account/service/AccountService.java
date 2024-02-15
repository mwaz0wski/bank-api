package es.nextdigital.account.service;

import es.nextdigital.account.model.Account;
import es.nextdigital.account.model.Movement;

import java.util.List;

public interface AccountService {
    List<Account> findAll();

    List<Account> findCustomerAccounts(String customer);

    Account getAccountDetail(String iban);

    List<Movement> getMovementsFromAccount(String iban);

}
