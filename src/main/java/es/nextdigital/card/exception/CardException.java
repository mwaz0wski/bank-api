package es.nextdigital.card.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CardException extends ResponseStatusException {
    public CardException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
