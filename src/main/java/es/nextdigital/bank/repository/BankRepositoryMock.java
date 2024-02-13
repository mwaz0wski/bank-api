package es.nextdigital.bank.repository;

import es.nextdigital.bank.model.Account;
import es.nextdigital.bank.model.Customer;
import es.nextdigital.bank.model.Movement;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;

@Repository
public class BankRepositoryMock implements BankRepository {
    private static Account account;
    static {
        var customer = Customer.builder().documentNumber("12345678Z").build();
        var movements = new ArrayList<Movement>();
        var ldt = LocalDateTime.of(2023, Month.SEPTEMBER, 22, 10, 15);
        movements.add(Movement.builder().amount(2300).type(Movement.MovementType.DEPOSIT).balance(2300).date(ldt).build());
        ldt = LocalDateTime.of(2023, Month.NOVEMBER, 15, 18, 15);
        movements.add(Movement.builder().amount(790).type(Movement.MovementType.WITHDRAWAL).balance(1510).date(ldt).build());
        ldt = LocalDateTime.of(2024, Month.JANUARY, 1, 0, 0);
        movements.add(Movement.builder().amount(10).type(Movement.MovementType.COMMISSION).balance(1500).date(ldt).build());

        account = Account
                .builder()
                .owner(customer)
                .balance(1500)
                .id("1")
                .movements(movements)
                .build();
    }
    @Override
    public Account findAccount(String id) {
        return account;
    }
}
