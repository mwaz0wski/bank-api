package es.nextdigital.bank.service;

import es.nextdigital.bank.model.Movement;

import java.util.List;

public interface BankService {
    List<Movement> findMovements(String accountId);
}
