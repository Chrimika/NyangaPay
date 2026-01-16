package com.example.nyangapay.service.analytics;

import com.example.nyangapay.model.IAccount;
import com.example.nyangapay.model.Transaction;

import org.springframework.stereotype.Component;

@Component
public class FraudDetectionVisitor implements IVisitor {
    @Override
    public void visit(IAccount account) {
        if (account.getBalance() < 0) {
            System.out.println("[ALERTE FRAUDE] Solde négatif détecté sur le compte : " + account.getAccountId());
        }
    }

    @Override
    public void visit(Transaction transaction) {
        if (transaction.getAmount() > 1000000) {
            System.out.println("[ALERTE FRAUDE] Transaction suspecte de gros montant : " + transaction.getAmount());
        }
    }
}
