package com.example.nyangapay.service;

import com.example.nyangapay.model.BasicAccount;
import com.example.nyangapay.model.IAccount;
import com.example.nyangapay.repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountService {

    private final IAccountRepository accountRepository;
    private final Map<String, AccountOpeningWorkflow> workflows;

    @Autowired
    public AccountService(IAccountRepository accountRepository, List<AccountOpeningWorkflow> workflowList) {
        this.accountRepository = accountRepository;
        this.workflows = workflowList.stream()
            .collect(Collectors.toMap(w -> {
                String name = w.getClass().getSimpleName().replace("OpeningWorkflow", "").toUpperCase();
                // Si la classe est BankAOpeningWorkflow, name est BANKA
                // Si on demande BANK_A, on doit matcher. On va ajouter des alias ou normaliser.
                if (name.equals("BANKA")) return "BANK_A";
                if (name.equals("MOBILEMONEY")) return "MOBILE_MONEY";
                return name;
            }, w -> w));
    }

    public void openAccount(String type, String id, double initialBalance) {
        AccountOpeningWorkflow workflow = workflows.get(type.toUpperCase());
        if (workflow == null) throw new IllegalArgumentException("Type d'opérateur inconnu : " + type);

        workflow.openAccount(id);
        IAccount account = new BasicAccount(id, initialBalance);
        accountRepository.save(account);
    }

    public Optional<IAccount> getAccount(String id, boolean debug) {
        return accountRepository.findById(id).map(account -> {
            if (debug) {
                return new LoggingDecorator(account);
            }
            return account;
        });
    }
}
