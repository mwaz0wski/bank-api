package es.nextdigital.card.model;

import lombok.Data;

@Data
public abstract sealed class Card permits CreditCard, DebitCard {
    private String id;
    private String pin;
    private String accountIban;
    private boolean active;
    private boolean requiresPinChange;
}
