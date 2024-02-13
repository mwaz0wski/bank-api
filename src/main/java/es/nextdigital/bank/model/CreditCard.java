package es.nextdigital.bank.model;

public class CreditCard extends Card {
    private Double availableCredit;
    @Override
    protected boolean withdraw(Double amount) {
        if (availableCredit - amount >= 0) {
            availableCredit -= amount;
            return true;
        }
        return false;
    }


}
