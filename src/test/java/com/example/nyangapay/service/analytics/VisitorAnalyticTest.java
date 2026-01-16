package com.example.nyangapay.service.analytics;

import com.example.nyangapay.model.BasicAccount;
import com.example.nyangapay.model.IAccount;
import com.example.nyangapay.model.Transaction;
import com.example.nyangapay.service.TransactionBuilder;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

public class VisitorAnalyticTest {

    @Test
    public void testMultipleAnalyticsInOnePass() {
        IAccount acc1 = new BasicAccount("ACC_001", 5000);
        IAccount acc2 = new BasicAccount("ACC_002", -100);
        
        Transaction tx1 = new TransactionBuilder("ACC_001", "ACC_003", 2000).build();
        Transaction tx2 = new TransactionBuilder("ACC_001", "ACC_004", 1500000).build();

        List<Object> items = Arrays.asList(acc1, acc2, tx1, tx2);

        CommissionAnalyticVisitor commissionVisitor = new CommissionAnalyticVisitor();
        FraudDetectionVisitor fraudVisitor = new FraudDetectionVisitor();

        System.out.println("--- Démarrage de l'analyse ---");
        for (Object item : items) {
            if (item instanceof IAccount) {
                ((IAccount) item).accept(commissionVisitor);
                ((IAccount) item).accept(fraudVisitor);
            } else if (item instanceof Transaction) {
                ((Transaction) item).accept(commissionVisitor);
                ((Transaction) item).accept(fraudVisitor);
            }
        }

        assertEquals(15020.0, commissionVisitor.getTotalCommissions(), 0.01);
    }
}
