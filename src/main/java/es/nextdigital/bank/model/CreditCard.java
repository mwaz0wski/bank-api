package es.nextdigital.bank.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public final class CreditCard extends Card {
    private Double availableCredit;
}
