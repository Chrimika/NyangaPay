package com.example.nyangapay.model;

public interface IAuthenticationStrategy {
    boolean authenticate(String identifier, String credentials);
    String getMethodName();
}
