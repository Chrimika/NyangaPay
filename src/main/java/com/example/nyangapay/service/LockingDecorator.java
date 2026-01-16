package com.example.nyangapay.service;

import com.example.nyangapay.model.IAccount;

public class LockingDecorator extends AccountDecorator {
    private boolean locked = false;

    public LockingDecorator(IAccount account) {
        super(account);
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    @Override
    public void executeTransaction(double amount) {
        if (locked) {
            System.out.println("[SECURE] Transaction refusée : le compte " + getAccountId() + " est VERROUILLÉ.");
            return;
        }
        super.executeTransaction(amount);
    }
}
