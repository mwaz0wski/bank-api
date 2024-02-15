package es.nextdigital.card.model;

import lombok.Data;

@Data
public class TransferRequest {
    private String targetAccountIban;
    private int targetAccountEntityCode;
    private double amount;
}
