package com.amazingenergy.saitamadomain.customer.repository;

import com.amazingenergy.core.repository.EntityRepository;
import com.amazingenergy.saitamadomain.customer.domain.attribute.CustomerOption;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import com.amazingenergy.saitamadomain.reference.domain.Language;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerOptionRepository extends EntityRepository<UUID, CustomerOption> {
    List<CustomerOption> listByStore(MerchantStore store, Language language);

    Optional<CustomerOption> getByCode(MerchantStore store, String optionCode);
}
