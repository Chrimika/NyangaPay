package com.example.nyangapay.service;

import com.example.nyangapay.model.IAccountValidator;
import com.example.nyangapay.model.IRateCalculator;

public interface IOperatorFactory {
    IAccountValidator createAccountValidator();
    IRateCalculator createRateCalculator();
}
