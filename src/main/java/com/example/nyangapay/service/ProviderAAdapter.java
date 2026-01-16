package com.example.nyangapay.service;

import org.springframework.stereotype.Component;

@Component
public class ProviderAAdapter implements ISmsProvider {
    private final ProviderAService legacyService = new ProviderAService();

    @Override
    public void sendSms(String phoneNumber, String message) {
        String json = "{\"to\": \"" + phoneNumber + "\", \"msg\": \"" + message + "\"}";
        legacyService.postJson(json);
    }
}

class ProviderAService {
    public void postJson(String json) {
        System.out.println("API Fournisseur A (JSON) : " + json);
    }
}
