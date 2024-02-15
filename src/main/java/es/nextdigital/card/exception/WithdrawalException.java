package es.nextdigital.card.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class WithdrawalException extends ResponseStatusException {
    public WithdrawalException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
