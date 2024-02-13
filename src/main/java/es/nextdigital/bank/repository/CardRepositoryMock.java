package es.nextdigital.bank.repository;

import es.nextdigital.bank.model.Card;
import es.nextdigital.bank.model.DebitCard;
import org.springframework.stereotype.Repository;

@Repository
public class CardRepositoryMock implements CardRepository {
    private static Card card;
    static {
        var debitCard = new DebitCard();
        debitCard.setId("1");
        debitCard.setPin("1234");
        debitCard.setActive(true);
        debitCard.setRequiresPinChange(false);
        debitCard.setAccount("1");
        debitCard.setDailyLimit(1000);
        card = debitCard;
    }
    @Override
    public Card findById(String cardId) {
        return card;
    }
}
