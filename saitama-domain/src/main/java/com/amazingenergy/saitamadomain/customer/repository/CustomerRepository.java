package com.amazingenergy.saitamadomain.customer.repository;

import com.amazingenergy.core.repository.AggregateRootRepository;
import com.amazingenergy.saitamadomain.common.Address;
import com.amazingenergy.saitamadomain.customer.domain.Customer;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository extends AggregateRootRepository<UUID, Customer> {
    List<Customer> getByName(String firstName);

    List<Customer> getListByStore(MerchantStore store);

    Optional<Customer> getByNick(String nick);

    Optional<Customer> getByNick(String nick, UUID storeId);

    Optional<Customer> getByNick(String nick, String code);
}
