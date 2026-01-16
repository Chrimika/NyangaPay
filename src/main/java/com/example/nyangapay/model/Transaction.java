package com.example.nyangapay.model;

import com.example.nyangapay.service.analytics.IVisitor;
import java.util.ArrayList;
import java.util.List;

public class Transaction {
    private final String sourceAccount;
    private final String destinationAccount;
    private final double amount;
    private final String currency;
    private final List<String> steps;
    private final boolean isComplete;

    public Transaction(String source, String destination, double amount, String currency, List<String> steps, boolean isComplete) {
        this.sourceAccount = source;
        this.destinationAccount = destination;
        this.amount = amount;
        this.currency = currency;
        this.steps = new ArrayList<>(steps);
        this.isComplete = isComplete;
    }

    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return String.format("Transaction de %.2f %s de %s vers %s. Étapes: %s. Version: %s",
                amount, currency, sourceAccount, destinationAccount, steps, isComplete ? "Complète" : "Courte");
    }

    public List<String> getSteps() { return steps; }
    public boolean isComplete() { return isComplete; }
    public double getAmount() { return amount; }
}
