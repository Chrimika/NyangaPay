package com.example.nyangapay.model;

import org.springframework.stereotype.Component;

@Component
public class AugmentedRealityAuthentication implements IAuthenticationStrategy {
    @Override
    public boolean authenticate(String identifier, String credentials) {
        System.out.println("Authentification par Réalité Augmentée pour : " + identifier);
        return true;
    }
    @Override
    public String getMethodName() { return "AUGMENTED_REALITY"; }
}
