package com.example.nyangapay.service.notification;

import com.example.nyangapay.service.ISmsProvider;
import com.example.nyangapay.service.ProviderAAdapter;
import com.example.nyangapay.service.ProviderBAdapter;
import org.junit.jupiter.api.Test;

public class SmsAdapterTest {

    @Test
    public void testSmsAdapters() {
        ISmsProvider providerA = new ProviderAAdapter();
        ISmsProvider providerB = new ProviderBAdapter();

        System.out.println("Test avec Fournisseur A :");
        providerA.sendSms("+237670000000", "Bonjour de NyangaPay!");

        System.out.println("Test avec Fournisseur B :");
        providerB.sendSms("+237690000000", "Solde insuffisant.");
    }
}
