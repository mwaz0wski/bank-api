package es.nextdigital.bank.repository;

import es.nextdigital.bank.model.Account;

public interface BankRepository {
    Account findAccount(String id);
}
