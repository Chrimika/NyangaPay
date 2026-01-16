package com.example.nyangapay.model;

import com.example.nyangapay.service.analytics.IVisitor;

public class BasicAccount implements IAccount {
    private final String id;
    private double balance;

    public BasicAccount(String id, double initialBalance) {
        this.id = id;
        this.balance = initialBalance;
    }

    @Override
    public void executeTransaction(double amount) {
        this.balance += amount;
        System.out.println("Transaction de " + amount + " sur le compte " + id + ". Nouveau solde: " + balance);
    }

    @Override
    public double getBalance() { return balance; }

    @Override
    public String getAccountId() { return id; }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }
}
