package es.nextdigital.card.service;

import static es.nextdigital.account.model.Movement.MovementType.WITHDRAWAL;

import es.nextdigital.account.repository.AccountRepository;
import es.nextdigital.card.exception.*;
import es.nextdigital.card.model.*;
import es.nextdigital.card.repository.CardRepository;
import es.nextdigital.commons.config.BankEntityConfig;
import es.nextdigital.commons.config.BankProperties;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.iban4j.IbanUtil;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {
    private final CardRepository cardRepository;
    private final AccountRepository accountRepository;
    private final BankProperties bankProperties;

    public Card findById(String cardId) {
        return null;
//        return cardRepository.findById(cardId).orElseThrow(() -> new CardException("Card not found"));
    }

    @Override
    public void withdrawFromCard(String cardId, Integer atmEntityCode, WithdrawRequest request) {
        var card = this.findById(cardId);
        var amount = request.getAmount();
        amount += this.getAtmOperationCommission(atmEntityCode);
        switch (card) {
            case CreditCard creditCard -> this.withdrawFromCreditCard(creditCard, amount);
            case DebitCard debitCard -> this.withdrawFromDebitCard(debitCard, amount);
        }
    }

    @Override
    public void depositIntoCard(String cardId, Integer atmEntityCode, DepositRequest request) {
        if (this.isExternalEntity(atmEntityCode)) {
            throw new DepositException("Deposits only available from customer's bank atm's");
        }
        var card = this.findById(cardId);
        var account = accountRepository.findByIban(card.getAccountIban());
        account.addMoney(request.getAmount());
        var movement = es.nextdigital.account.model.Movement.builder()
                .amount(request.getAmount())
                .balance(account.getBalance())
                .type(es.nextdigital.account.model.Movement.MovementType.DEPOSIT)
                .date(LocalDateTime.now())
                .build();
        account.addMovement(movement);
    }

    @Override
    public void performTransferToAccount(String iban, TransferRequest request) {
        this.validateIban(request.getTargetAccountIban());

        var account = accountRepository.findByIban(iban);
        var commission = this.getTransferCommission(request.getTargetAccountEntityCode());
        var totalAmount = request.getAmount() + commission;
        if (account.hasEnoughBalanceForDeduction(totalAmount)) {
            account.deductMoney(totalAmount);
            var movement = es.nextdigital.account.model.Movement.builder()
                    .type(es.nextdigital.account.model.Movement.MovementType.OUTGOING_TRANSFER)
                    .amount(totalAmount)
                    .balance(account.getBalance())
                    .date(LocalDateTime.now())
                    .build();
            account.addMovement(movement);
        } else {
            throw new TransferException("Not enough funds in the account");
        }
    }

    private void validateIban(String targetAccountIban) {
        try {
            IbanUtil.validate(targetAccountIban);
        } catch (Exception e) {
            throw new IbanValidationException();
        }
    }

    private double getAtmOperationCommission(Integer entityCode) {
        return bankProperties.getExternalBankEntitiesConfig().stream()
                .filter(entityConfig -> entityConfig.getId() == entityCode)
                .map(BankEntityConfig::getAtmOperationComission)
                .findFirst()
                .orElse(0.0);
    }

    private double getTransferCommission(Integer entityCode) {
        return bankProperties.getExternalBankEntitiesConfig().stream()
                .filter(entityConfig -> entityConfig.getId() == entityCode)
                .map(BankEntityConfig::getOutgoingTransferComission)
                .findFirst()
                .orElse(0.0);
    }

    private boolean isExternalEntity(Integer entityCode) {
        return bankProperties.getExternalBankEntitiesConfig().stream()
                .anyMatch(entityConfig -> entityConfig.getId() == entityCode);
    }

    private void withdrawFromCreditCard(CreditCard creditCard, double amount) {
        if (creditCard.hasEnoughCreditForWithdrawal(amount)) {
            creditCard.withdrawCredit(amount);
            // Introduce batch operation for deducting the amount when the card is configured to
        } else {
            throw new WithdrawalException("Credit limit exceeded");
        }
    }

    private void withdrawFromDebitCard(DebitCard debitCard, double amount) {
        var account = accountRepository.findByIban(debitCard.getAccountIban());
        if (account.getBalance() - amount >= 0 && debitCard.withdrawalCanBePerformed(amount)) {
            debitCard.performWithdrawal(amount);
            account.deductMoney(amount);
            var movement = es.nextdigital.account.model.Movement.builder()
                    .amount(amount)
                    .balance(account.getBalance())
                    .type(WITHDRAWAL)
                    .date(LocalDateTime.now())
                    .build();
            account.addMovement(movement);
        } else {
            throw new WithdrawalException("Credit limit exceeded");
        }
    }
}
