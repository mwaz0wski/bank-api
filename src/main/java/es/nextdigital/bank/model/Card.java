package es.nextdigital.bank.model;

import lombok.Data;

@Data
public abstract sealed class Card permits CreditCard, DebitCard  {
    private String id;
    private String pin;
    private String account;
    private boolean active;
    private boolean requiresPinChange;
}
