package es.nextdigital.commons.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Customer {
    private String documentNumber;
}
