package es.nextdigital.bank.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Customer {
    private String documentNumber;
}
