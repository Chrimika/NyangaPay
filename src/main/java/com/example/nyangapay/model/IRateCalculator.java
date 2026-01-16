package com.example.nyangapay.model;

public interface IRateCalculator {
    double calculateRate(double amount);
    String getRateInfo();
}
