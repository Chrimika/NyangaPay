package com.example.nyangapay.service;

import com.example.nyangapay.model.IAccountValidator;
import com.example.nyangapay.model.IRateCalculator;

import org.springframework.stereotype.Component;

@Component
public class BankAFactory implements IOperatorFactory {
    @Override
    public IAccountValidator createAccountValidator() {
        return new BankAAccountValidator();
    }

    @Override
    public IRateCalculator createRateCalculator() {
        return new BankARateCalculator();
    }
}

class BankAAccountValidator implements IAccountValidator {
    @Override
    public boolean validate(String accountId) {
        return accountId.startsWith("BANK_A_");
    }
    @Override
    public String getValidatorInfo() { return "Validateur BankA (Format: BANK_A_xxx)"; }
}

class BankARateCalculator implements IRateCalculator {
    @Override
    public double calculateRate(double amount) {
        return amount * 0.02; // 2% fixed rate
    }
    @Override
    public String getRateInfo() { return "Calculateur BankA (2% fixe)"; }
}
