package com.example.nyangapay.repository;

import com.example.nyangapay.model.Transaction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryTransactionRepository {
    private final List<Transaction> transactions = new ArrayList<>();

    public void save(Transaction transaction) {
        transactions.add(transaction);
        System.out.println("Transaction sauvegardée en mémoire.");
    }

    public List<Transaction> findAll() {
        return new ArrayList<>(transactions);
    }
}
