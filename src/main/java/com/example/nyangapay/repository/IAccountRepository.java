package com.example.nyangapay.repository;

import com.example.nyangapay.model.IAccount;
import java.util.Optional;

public interface IAccountRepository {
    void save(IAccount account);
    Optional<IAccount> findById(String id);
}
