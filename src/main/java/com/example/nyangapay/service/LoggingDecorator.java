package com.example.nyangapay.service;

import com.example.nyangapay.model.IAccount;

public class LoggingDecorator extends AccountDecorator {
    public LoggingDecorator(IAccount account) {
        super(account);
    }

    @Override
    public void executeTransaction(double amount) {
        System.out.println("[LOG] Début de transaction pour : " + getAccountId());
        super.executeTransaction(amount);
        System.out.println("[LOG] Fin de transaction. Solde final : " + getBalance());
    }
}
