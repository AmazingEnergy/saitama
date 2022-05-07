package com.amazingenergy.saitamadomain.customer.repository;

import com.amazingenergy.core.repository.EntityRepository;
import com.amazingenergy.saitamadomain.customer.domain.attribute.CustomerOptionValue;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import com.amazingenergy.saitamadomain.reference.domain.Language;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerOptionValueRepository extends EntityRepository<UUID, CustomerOptionValue> {
    List<CustomerOptionValue> listByStore(MerchantStore store, Language language);

    Optional<CustomerOptionValue> getByCode(MerchantStore store, String optionValueCode);
}
