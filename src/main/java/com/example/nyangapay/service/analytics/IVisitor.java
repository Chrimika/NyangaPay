package com.example.nyangapay.service.analytics;

import com.example.nyangapay.model.IAccount;
import com.example.nyangapay.model.Transaction;

public interface IVisitor {
    void visit(IAccount account);
    void visit(Transaction transaction);
}
