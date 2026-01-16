package com.example.nyangapay.service;

import com.example.nyangapay.model.Transaction;
import java.util.ArrayList;
import java.util.List;

public class TransactionBuilder {
    protected String sourceAccount;
    protected String destinationAccount;
    protected double amount;
    protected String currency = "XAF";
    protected List<String> steps = new ArrayList<>();
    protected boolean isComplete = false;

    public TransactionBuilder(String from, String to, double amount) {
        this.sourceAccount = from;
        this.destinationAccount = to;
        this.amount = amount;
        this.steps.add("Initialisation");
    }

    public TransactionBuilder applyConversion(String toCurrency, double rate) {
        this.currency = toCurrency;
        this.amount *= rate;
        this.steps.add("Conversion devise (" + toCurrency + ")");
        return this;
    }

    public TransactionBuilder applyCommission(String name, double fee) {
        this.amount += fee;
        this.steps.add("Commission: " + name);
        return this;
    }

    public TransactionBuilder withNotification() {
        this.steps.add("Notification SMS");
        return this;
    }

    public TransactionBuilder withJournaling() {
        this.steps.add("Journalisation détaillée");
        return this;
    }

    public Transaction build() {
        this.steps.add("Finalisation");
        return new Transaction(sourceAccount, destinationAccount, amount, currency, steps, isComplete);
    }

    public Transaction buildShort() {
        this.isComplete = false;
        return build();
    }

    public Transaction buildFull() {
        this.isComplete = true;
        this.withNotification().withJournaling();
        return build();
    }
}
