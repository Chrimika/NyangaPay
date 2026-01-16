package com.example.nyangapay.service.workflow;

import com.example.nyangapay.service.AccountOpeningWorkflow;
import com.example.nyangapay.service.BankAOpeningWorkflow;
import com.example.nyangapay.service.MobileMoneyOpeningWorkflow;
import org.junit.jupiter.api.Test;

public class WorkflowTemplateTest {

    @Test
    public void testWorkflows() {
        System.out.println("--- Workflow Banque A ---");
        AccountOpeningWorkflow bankA = new BankAOpeningWorkflow();
        bankA.openAccount("Client Premium");

        System.out.println("\n--- Workflow Mobile Money ---");
        AccountOpeningWorkflow mobile = new MobileMoneyOpeningWorkflow();
        mobile.openAccount("Utilisateur Prepaid");
    }
}
