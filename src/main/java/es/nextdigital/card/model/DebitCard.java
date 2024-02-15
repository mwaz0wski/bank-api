package es.nextdigital.card.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public final class DebitCard extends Card {
    private double dailyLimit;

    public boolean withdrawalCanBePerformed(double amount) {
        return dailyLimit - amount >= 0;
    }

    public void performWithdrawal(double amount) {
        this.dailyLimit -= amount;
    }
}
