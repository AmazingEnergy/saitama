package com.amazingenergy.core.moneytransfer.repository;


import com.amazingenergy.core.moneytransfer.domain.Account;
import com.amazingenergy.core.repository.AggregateRootRepository;

import java.util.Optional;

public interface AccountRepository extends AggregateRootRepository<Integer, Account> {
    Optional<Account> getById(int id);
}
