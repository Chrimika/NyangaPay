package com.example.nyangapay.model;

import com.example.nyangapay.service.analytics.IVisitor;

public interface IAccount {
    void executeTransaction(double amount);
    double getBalance();
    String getAccountId();
    void accept(IVisitor visitor);
}
