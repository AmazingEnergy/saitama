package com.amazingenergy.core.moneytransfer.repository;


import com.amazingenergy.core.moneytransfer.domain.Account;
import com.amazingenergy.core.repository.AggregateRootRepository;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends AggregateRootRepository<UUID, Account> {
}
