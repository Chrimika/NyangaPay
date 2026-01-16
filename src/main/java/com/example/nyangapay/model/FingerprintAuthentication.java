package com.example.nyangapay.model;

import org.springframework.stereotype.Component;

@Component
public class FingerprintAuthentication implements IAuthenticationStrategy {
    @Override
    public boolean authenticate(String identifier, String credentials) {
        System.out.println("Authentification par empreinte digitale pour : " + identifier);
        return true; 
    }
    @Override
    public String getMethodName() { return "FINGERPRINT"; }
}
