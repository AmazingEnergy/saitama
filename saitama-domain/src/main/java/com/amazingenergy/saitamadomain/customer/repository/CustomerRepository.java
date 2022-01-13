package com.amazingenergy.saitamadomain.customer.repository;

import com.amazingenergy.core.repository.Repository;
import com.amazingenergy.saitamadomain.customer.domain.Customer;

import java.util.UUID;

public interface CustomerRepository extends Repository<Customer> {
    Iterable<Customer> findAll();
    Customer findById(UUID id);
}
