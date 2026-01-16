package com.example.nyangapay.service.analytics;

import com.example.nyangapay.model.IAccount;
import com.example.nyangapay.model.Transaction;

import org.springframework.stereotype.Component;

@Component
public class CommissionAnalyticVisitor implements IVisitor {
    private double totalCommissions = 0;

    @Override
    public void visit(IAccount account) {}

    @Override
    public void visit(Transaction transaction) {
        totalCommissions += transaction.getAmount() * 0.01;
    }

    public double getTotalCommissions() {
        return totalCommissions;
    }
}
