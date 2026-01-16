package com.example.nyangapay.service;

import com.example.nyangapay.model.IAccount;
import com.example.nyangapay.service.analytics.IVisitor;

public abstract class AccountDecorator implements IAccount {
    protected final IAccount decoratedAccount;

    public AccountDecorator(IAccount account) {
        this.decoratedAccount = account;
    }

    @Override
    public void executeTransaction(double amount) {
        decoratedAccount.executeTransaction(amount);
    }

    @Override
    public double getBalance() {
        return decoratedAccount.getBalance();
    }

    @Override
    public String getAccountId() {
        return decoratedAccount.getAccountId();
    }

    @Override
    public void accept(IVisitor visitor) {
        decoratedAccount.accept(visitor);
    }
}
