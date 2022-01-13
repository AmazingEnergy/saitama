package com.amazingenergy.core.moneytransfer.repository;


import com.amazingenergy.core.moneytransfer.domain.Account;
import com.amazingenergy.core.repository.Repository;

import java.util.Optional;

public interface AccountRepository extends Repository<Account> {
    Optional<Account> getById(int id);
}