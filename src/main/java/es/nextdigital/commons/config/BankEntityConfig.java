package es.nextdigital.commons.config;

import lombok.Data;

@Data
public class BankEntityConfig {
    private int id;
    private String name;
    private double atmOperationComission;
    private double outgoingTransferComission;
}
