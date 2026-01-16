package com.example.nyangapay.service.operator;

import com.example.nyangapay.service.BankAFactory;
import com.example.nyangapay.service.MobileMoneyFactory;
import com.example.nyangapay.service.IOperatorFactory;
import com.example.nyangapay.model.IAccountValidator;
import com.example.nyangapay.model.IRateCalculator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OperatorFactoryTest {

    @Test
    public void testBankAFactoryCoherence() {
        IOperatorFactory factory = new BankAFactory();
        IAccountValidator validator = factory.createAccountValidator();
        IRateCalculator calculator = factory.createRateCalculator();

        assertTrue(validator.validate("BANK_A_12345"));
        assertFalse(validator.validate("+237670000000"));
        assertEquals(20.0, calculator.calculateRate(1000.0));
        assertTrue(validator.getValidatorInfo().contains("BankA"));
    }

    @Test
    public void testMobileMoneyFactoryCoherence() {
        IOperatorFactory factory = new MobileMoneyFactory();
        IAccountValidator validator = factory.createAccountValidator();
        IRateCalculator calculator = factory.createRateCalculator();

        assertTrue(validator.validate("+237670000000"));
        assertFalse(validator.validate("BANK_A_12345"));
        assertEquals(10.0, calculator.calculateRate(500.0));
        assertEquals(50.0, calculator.calculateRate(5000.0));
        assertTrue(validator.getValidatorInfo().contains("MobileMoney"));
    }

    @Test
    public void testPolymorphicOperatorSwitch() {
        IOperatorFactory factory;
        factory = new BankAFactory();
        performOperations(factory, "BANK_A_001", 1000);
        factory = new MobileMoneyFactory();
        performOperations(factory, "+237677777777", 1000);
    }

    private void performOperations(IOperatorFactory factory, String account, double amount) {
        IAccountValidator v = factory.createAccountValidator();
        IRateCalculator r = factory.createRateCalculator();
        assertTrue(v.validate(account));
        System.out.println("Processing " + account + " with " + r.getRateInfo());
    }
}
