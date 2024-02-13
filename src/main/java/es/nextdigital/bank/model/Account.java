package es.nextdigital.bank.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private String id;
    private Customer owner;
    private double balance;
    private List<Movement> movements;
}
