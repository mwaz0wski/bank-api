package es.nextdigital.bank.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BankEntity {
    private String name;
    private Double comission;
}
