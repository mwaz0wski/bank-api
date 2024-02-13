package es.nextdigital.bank.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Movement {
    private double amount;
    private MovementType type;
    private double balance;
    private LocalDateTime date;


    public double getAmount() {
        if (type.isSubstractor) {
            return - amount;
        }
        return amount;
    }

    public enum MovementType {
        DEPOSIT(false), WITHDRAWAL(true), COMMISSION(true), INCOMING_TRANSFER(false), OUTGOING_TRANSFER(true);
        final boolean isSubstractor;

        MovementType(boolean isSubstractor) {
            this.isSubstractor = isSubstractor;
        }
    }
}
