package es.nextdigital.bank.model;

import lombok.Data;

@Data
public class Movement {
    private Double amount;
    private MovementType type;

    public enum MovementType {
        DEPOSIT, WITHDRAWAL, COMMISSION, INCOMING_TRANSFER, OUTGOING_TRANSFER;
    }
}
