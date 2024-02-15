package es.nextdigital.card.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class TransferException extends ResponseStatusException {
    public TransferException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
