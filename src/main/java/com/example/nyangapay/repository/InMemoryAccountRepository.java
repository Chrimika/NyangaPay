package com.example.nyangapay.repository;

import com.example.nyangapay.model.IAccount;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class InMemoryAccountRepository implements IAccountRepository {
    private final Map<String, IAccount> db = new HashMap<>();

    @Override
    public void save(IAccount account) {
        db.put(account.getAccountId(), account);
        System.out.println("Compte sauvegardé en mémoire: " + account.getAccountId());
    }

    @Override
    public Optional<IAccount> findById(String id) {
        return Optional.ofNullable(db.get(id));
    }
    
    public Map<String, IAccount> findAll() {
        return db;
    }
}
