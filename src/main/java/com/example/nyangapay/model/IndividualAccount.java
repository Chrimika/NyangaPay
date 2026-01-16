package com.example.nyangapay.model;

public class IndividualAccount implements INotificationTarget {
    private final String accountId;

    public IndividualAccount(String accountId) {
        this.accountId = accountId;
    }

    @Override
    public void receive(String message) {
        System.out.println("Notification individuelle pour [" + accountId + "] : " + message);
    }
}
