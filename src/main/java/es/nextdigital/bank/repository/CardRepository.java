package es.nextdigital.bank.repository;

import es.nextdigital.bank.model.Card;

public interface CardRepository {
    Card findById(String cardId);
}
