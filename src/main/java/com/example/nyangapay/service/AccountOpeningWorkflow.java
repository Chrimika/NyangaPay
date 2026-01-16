package com.example.nyangapay.service;

public abstract class AccountOpeningWorkflow {
    public final void openAccount(String details) {
        verifyDocuments(details);
        verifyBackground();
        finaliseOpening();
        notifyClient();
    }
    protected void verifyDocuments(String details) {
        System.out.println("Vérification des documents standards : " + details);
    }
    protected abstract void verifyBackground();
    protected void finaliseOpening() {
        System.out.println("Création du compte dans la base de données centrale.");
    }
    protected void notifyClient() {
        System.out.println("Envoi du message de bienvenue par SMS.");
    }
}
