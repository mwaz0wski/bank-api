package es.nextdigital.bank.repository;

import es.nextdigital.bank.model.Account;

public interface AccountRepository {
    Account findById(String id);
}
