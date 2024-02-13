package es.nextdigital.bank.model;

public abstract class Card  {
    protected String id;
    private String pin;
    protected BankAccount account;
    protected boolean active;
    protected boolean requiresActivation;
    protected abstract boolean withdraw(Double amount);
    protected void deposit(Double amount) {
        this.account.setBalance(this.account.getBalance() + amount);
    }
}
