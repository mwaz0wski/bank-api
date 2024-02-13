package es.nextdigital.bank.model;

import lombok.Data;

import java.util.List;

@Data
public class BankAccount {
    private String id;
    private Customer owner;
    private Double balance;
    private List<Movement> movements;
}
