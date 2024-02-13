package es.nextdigital.bank.exception;

import lombok.With;

public class WithdrawalException extends RuntimeException {
    public WithdrawalException(String message) {
        super(message);
    }
}
