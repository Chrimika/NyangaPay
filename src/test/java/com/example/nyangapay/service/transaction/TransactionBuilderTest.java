package com.example.nyangapay.service.transaction;

import com.example.nyangapay.model.Transaction;
import com.example.nyangapay.service.TransactionBuilder;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TransactionBuilderTest {

    @Test
    public void testShortTransaction() {
        Transaction tx = new TransactionBuilder("ACC1", "ACC2", 5000)
                .applyCommission("Base", 50)
                .buildShort();

        System.out.println(tx);
        assertFalse(tx.isComplete());
        assertTrue(tx.getSteps().contains("Commission: Base"));
        assertFalse(tx.getSteps().contains("Notification SMS"));
    }

    @Test
    public void testFullTransactionWithConversion() {
        Transaction tx = new TransactionBuilder("ACC1", "ACC2", 100)
                .applyConversion("EUR", 0.0015)
                .applyCommission("Service", 0.5)
                .buildFull();

        System.out.println(tx);
        assertTrue(tx.isComplete());
        assertTrue(tx.getSteps().contains("Conversion devise (EUR)"));
        assertTrue(tx.getSteps().contains("Notification SMS"));
        assertTrue(tx.getSteps().contains("Journalisation détaillée"));
    }

    @Test
    public void testFluentApiFlexibility() {
        Transaction tx = new TransactionBuilder("A", "B", 1000)
                .withNotification()
                .applyCommission("Tax", 10)
                .build();
        
        assertEquals(2, tx.getSteps().stream().filter(s -> !s.equals("Initialisation") && !s.equals("Finalisation")).count());
    }
}
