package com.example.nyangapay.service;

import com.example.nyangapay.model.AugmentedRealityAuthentication;
import com.example.nyangapay.model.FingerprintAuthentication;
import com.example.nyangapay.model.IAuthenticationStrategy;
import com.example.nyangapay.model.PasswordAuthentication;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthenticationFactory {
    private final Map<String, IAuthenticationStrategy> strategies;

    @Autowired
    public AuthenticationFactory(List<IAuthenticationStrategy> strategyList) {
        this.strategies = strategyList.stream()
            .collect(Collectors.toMap(IAuthenticationStrategy::getMethodName, s -> s));
    }

    public Optional<IAuthenticationStrategy> getStrategy(String methodName) {
        return Optional.ofNullable(strategies.get(methodName));
    }
}
