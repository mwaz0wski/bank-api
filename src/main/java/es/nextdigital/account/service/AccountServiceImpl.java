package es.nextdigital.account.service;

import es.nextdigital.account.model.Account;
import es.nextdigital.account.model.Movement;
import es.nextdigital.account.repository.AccountRepository;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public List<Account> findCustomerAccounts(String documentNumber) {
        return accountRepository.findByCustomer(documentNumber);
    }

    @Override
    public Account getAccountDetail(String iban) {
        return accountRepository.findByIban(iban);
    }

    @Override
    public List<Movement> getMovementsFromAccount(String iban) {
        return this.getAccountDetail(iban).getMovements();
    }
}
