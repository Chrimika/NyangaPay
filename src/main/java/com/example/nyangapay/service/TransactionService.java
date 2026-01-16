package com.example.nyangapay.service;

import com.example.nyangapay.model.IAccount;
import com.example.nyangapay.model.Transaction;
import com.example.nyangapay.repository.IAccountRepository;
import com.example.nyangapay.repository.InMemoryTransactionRepository;
import com.example.nyangapay.service.analytics.CommissionAnalyticVisitor;
import com.example.nyangapay.service.analytics.FraudDetectionVisitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    private final IAccountRepository accountRepository;
    private final InMemoryTransactionRepository transactionRepository;
    private final CommissionAnalyticVisitor commissionVisitor;
    private final FraudDetectionVisitor fraudVisitor;

    @Autowired
    public TransactionService(IAccountRepository accountRepository,
                              InMemoryTransactionRepository transactionRepository,
                              CommissionAnalyticVisitor commissionVisitor,
                              FraudDetectionVisitor fraudVisitor) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.commissionVisitor = commissionVisitor;
        this.fraudVisitor = fraudVisitor;
    }

    public Transaction executeTransfer(String from, String to, double amount, boolean full) {
        IAccount src = accountRepository.findById(from)
                .orElseThrow(() -> new IllegalArgumentException("Compte source inexistant: " + from));
        IAccount dest = accountRepository.findById(to)
                .orElseThrow(() -> new IllegalArgumentException("Compte destination inexistant: " + to));

        TransactionBuilder builder = new TransactionBuilder(from, to, amount);
        Transaction tx = full ? builder.buildFull() : builder.buildShort();

        src.executeTransaction(-amount);
        dest.executeTransaction(amount);

        transactionRepository.save(tx);
        return tx;
    }

    public String runAnalytics() {
        List<Transaction> transactions = transactionRepository.findAll();
        
        System.out.println("--- Analyse Springified en cours ---");
        for (Transaction tx : transactions) {
            tx.accept(commissionVisitor);
            tx.accept(fraudVisitor);
        }
        
        return "Analyse terminée. Total commissions: " + commissionVisitor.getTotalCommissions();
    }
}
