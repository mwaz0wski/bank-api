package es.nextdigital.bank.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public final class DebitCard extends Card {
    private double dailyLimit;
}
