package es.nextdigital.bank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class DepositException extends ResponseStatusException {
    public DepositException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
