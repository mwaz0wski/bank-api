package es.nextdigital.card.service;

import es.nextdigital.card.model.DepositRequest;
import es.nextdigital.card.model.TransferRequest;
import es.nextdigital.card.model.WithdrawRequest;

public interface CardService {
    void performTransferToAccount(String cardId, TransferRequest request);

    void withdrawFromCard(String cardId, Integer atmEntityCode, WithdrawRequest request);

    void depositIntoCard(String cardId, Integer atmEntityCode, DepositRequest request);
}
