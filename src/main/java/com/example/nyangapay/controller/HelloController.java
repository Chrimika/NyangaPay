package com.example.nyangapay.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String index() {
        return "Bienvenue sur NyangaPay API!";
    }

    @GetMapping("/health")
    public String health() {
        return "OK";
    }
}
