package com.example.nyangapay.service;

import org.springframework.stereotype.Component;

@Component
public class MobileMoneyOpeningWorkflow extends AccountOpeningWorkflow {
    @Override
    protected void verifyBackground() {
        System.out.println("Mobile Money : Vérification de l'identité via l'opérateur mobile (Sim Registry).");
    }
}
