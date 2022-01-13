package com.amazingenergy.saitamadomain.payment.repository;

import com.amazingenergy.core.repository.Repository;
import com.amazingenergy.saitamadomain.payment.domain.Transaction;

import java.util.UUID;

public interface TransactionRepository extends Repository<Transaction> {
    Iterable<Transaction> findAll();
    Transaction findById(UUID id);
}
