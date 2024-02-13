package es.nextdigital.bank.model;

public class DebitCard extends Card {
    private double dailyLimit;
    @Override
    protected boolean withdraw(Double amount) {
        var balance = this.account.getBalance();
        if (balance >= amount && this.dailyLimit - balance >= 0) {
            this.account.setBalance(balance - amount);
            this.dailyLimit -= amount;
            return true;
        }
        return false;
    }
}
