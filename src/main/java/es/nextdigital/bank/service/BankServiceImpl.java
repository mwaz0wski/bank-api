package es.nextdigital.bank.service;

import es.nextdigital.bank.model.Movement;
import es.nextdigital.bank.repository.BankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BankServiceImpl implements BankService {
    private final BankRepository bankRepository;
    @Override
    public List<Movement> findMovements(String accountId) {
        return bankRepository.findAccount(accountId).getMovements();
    }
}
