package com.example.nyangapay.controller;

import com.example.nyangapay.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/open")
    public ResponseEntity<String> openAccount(@RequestParam String type, @RequestParam String id, @RequestParam double initialBalance) {
        try {
            accountService.openAccount(type, id, initialBalance);
            return ResponseEntity.ok("Compte ouvert avec succès via le workflow " + type);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAccount(@PathVariable String id, @RequestParam(defaultValue = "false") boolean debug) {
        return accountService.getAccount(id, debug)
                .map(account -> ResponseEntity.ok("Compte: " + account.getAccountId() + ", Solde: " + account.getBalance()))
                .orElse(ResponseEntity.notFound().build());
    }
}
