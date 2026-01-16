package com.example.nyangapay.model;

import org.springframework.stereotype.Component;

@Component
public class PasswordAuthentication implements IAuthenticationStrategy {
    @Override
    public boolean authenticate(String identifier, String credentials) {
        System.out.println("Authentification par mot de passe pour : " + identifier);
        return "root".equals(credentials);
    }
    @Override
    public String getMethodName() { return "PASSWORD"; }
}
