package es.nextdigital.card.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public final class CreditCard extends Card {
    private double availableCredit;

    public boolean hasEnoughCreditForWithdrawal(double amount) {
        return availableCredit - amount >= 0;
    }

    public void withdrawCredit(double amount) {
        this.availableCredit -= amount;
    }
}
