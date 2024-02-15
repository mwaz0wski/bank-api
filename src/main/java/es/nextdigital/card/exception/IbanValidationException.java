package es.nextdigital.card.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class IbanValidationException extends ResponseStatusException {
    public IbanValidationException() {
        super(HttpStatus.BAD_REQUEST, "Invalid IBAN");
    }
}
