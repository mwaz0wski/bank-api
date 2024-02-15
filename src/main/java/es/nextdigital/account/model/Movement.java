package es.nextdigital.account.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
public class Movement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double amount;
    private MovementType type;
    private double balance;
    private LocalDateTime date;

    public double getAmount() {
        if (type.isSubstractor) {
            return -amount;
        }
        return amount;
    }

    public enum MovementType {
        DEPOSIT(false),
        WITHDRAWAL(true),
        COMMISSION(true),
        INCOMING_TRANSFER(false),
        OUTGOING_TRANSFER(true);
        final boolean isSubstractor;

        MovementType(boolean isSubstractor) {
            this.isSubstractor = isSubstractor;
        }
    }
}
