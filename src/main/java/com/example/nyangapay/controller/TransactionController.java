package com.example.nyangapay.controller;

import com.example.nyangapay.model.Transaction;
import com.example.nyangapay.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(@RequestParam String from, @RequestParam String to, @RequestParam double amount, @RequestParam(defaultValue = "false") boolean full) {
        try {
            Transaction tx = transactionService.executeTransfer(from, to, amount, full);
            return ResponseEntity.ok("Transaction effectuée : " + tx.toString());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/analytics")
    public ResponseEntity<String> runAnalytics() {
        return ResponseEntity.ok(transactionService.runAnalytics());
    }
}
