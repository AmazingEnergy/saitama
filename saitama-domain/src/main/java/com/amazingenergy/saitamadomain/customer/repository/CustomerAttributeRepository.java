package com.amazingenergy.saitamadomain.customer.repository;

import com.amazingenergy.core.repository.EntityRepository;
import com.amazingenergy.saitamadomain.customer.domain.Customer;
import com.amazingenergy.saitamadomain.customer.domain.attribute.CustomerAttribute;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerAttributeRepository extends EntityRepository<UUID, CustomerAttribute> {
    Optional<CustomerAttribute> getByCustomerOptionId(MerchantStore store, UUID customerId, UUID id);

    List<CustomerAttribute> getByCustomerOptionValueId(MerchantStore store, UUID id);

    List<CustomerAttribute> getByOptionId(MerchantStore store, UUID id);

    List<CustomerAttribute> getByCustomer(MerchantStore store, Customer customer);
}
