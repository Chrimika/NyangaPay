package com.example.nyangapay.service;

import org.springframework.stereotype.Component;

@Component
public class BankAOpeningWorkflow extends AccountOpeningWorkflow {
    @Override
    protected void verifyBackground() {
        System.out.println("Banque A : Recherche d'antécédents bancaires (Interbank Check).");
    }
    @Override
    protected void verifyDocuments(String details) {
        super.verifyDocuments(details);
        System.out.println("Banque A : Vérification supplémentaire du justificatif de domicile.");
    }
}
