package com.example.nyangapay.controller;

import com.example.nyangapay.model.IAuthenticationStrategy;
import com.example.nyangapay.service.AuthenticationFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationFactory authFactory;

    @Autowired
    public AuthController(AuthenticationFactory authFactory) {
        this.authFactory = authFactory;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestParam String method,
            @RequestParam String user,
            @RequestParam String credentials) {
            
        return authFactory.getStrategy(method)
                .map(strategy -> {
                    boolean result = strategy.authenticate(user, credentials);
                    if (result) {
                        return ResponseEntity.ok("Authentification réussie via " + method);
                    } else {
                        return ResponseEntity.status(401).body("Échec de l'authentification via " + method);
                    }
                })
                .orElse(ResponseEntity.badRequest().body("Méthode d'authentification inconnue : " + method));
    }
}
