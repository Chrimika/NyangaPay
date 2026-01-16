package com.example.nyangapay.model;

public interface IAccountValidator {
    boolean validate(String accountId);
    String getValidatorInfo();
}
