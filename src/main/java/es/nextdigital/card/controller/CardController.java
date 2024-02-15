package es.nextdigital.card.controller;

import es.nextdigital.card.model.DepositRequest;
import es.nextdigital.card.model.TransferRequest;
import es.nextdigital.card.model.WithdrawRequest;
import es.nextdigital.card.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bank/api/v1/cards")
public class CardController {
    private final CardService cardService;

    @PostMapping("/{cardId}/transfer")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void performTransferToAccount(@PathVariable String cardId, @RequestBody TransferRequest request) {
        cardService.performTransferToAccount(cardId, request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/cards/{cardId}/withdrawal")
    public void withdrawFromCard(
            @PathVariable String cardId, @RequestParam Integer atmEntityCode, @RequestBody WithdrawRequest request) {
        cardService.withdrawFromCard(cardId, atmEntityCode, request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/cards/{cardId}/deposit")
    public void depositIntoCard(
            @PathVariable String cardId, @RequestParam Integer atmEntityCode, @RequestBody DepositRequest request) {
        cardService.depositIntoCard(cardId, atmEntityCode, request);
    }
}
