package com.example.nyangapay.service;

public interface ISmsProvider {
    void sendSms(String phoneNumber, String message);
}
