package es.nextdigital.account.model;

import jakarta.persistence.*;

import java.util.List;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;

@Data
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String iban;

    private String customer;
    private double balance;

    @OneToMany
    private List<Movement> movements;

    public void addMoney(double amount) {
        this.balance += amount;
    }

    public void deductMoney(double amount) {
        this.balance -= amount;
    }

    public void addMovement(Movement movement) {
        if (CollectionUtils.isNotEmpty(movements)) {
            movements.add(movement);
        }
    }

    public boolean hasEnoughBalanceForDeduction(double amount) {
        return this.balance - amount >= 0;
    }
}
