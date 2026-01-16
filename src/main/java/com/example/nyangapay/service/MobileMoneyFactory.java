package com.example.nyangapay.service;

import com.example.nyangapay.model.IAccountValidator;
import com.example.nyangapay.model.IRateCalculator;

import org.springframework.stereotype.Component;

@Component
public class MobileMoneyFactory implements IOperatorFactory {
    @Override
    public IAccountValidator createAccountValidator() {
        return new MobileMoneyAccountValidator();
    }

    @Override
    public IRateCalculator createRateCalculator() {
        return new MobileMoneyRateCalculator();
    }
}

class MobileMoneyAccountValidator implements IAccountValidator {
    @Override
    public boolean validate(String accountId) {
        return accountId.matches("^\\+237[0-9]{9}$");
    }
    @Override
    public String getValidatorInfo() { return "Validateur MobileMoney (Format: +237xxxxxxxx)"; }
}

class MobileMoneyRateCalculator implements IRateCalculator {
    @Override
    public double calculateRate(double amount) {
        if (amount < 1000) return 10.0;
        return amount * 0.01;
    }
    @Override
    public String getRateInfo() { return "Calculateur MobileMoney (10 FCFA ou 1%)"; }
}
