package com.amazingenergy.saitamadomain.customer.repository;

import com.amazingenergy.core.repository.EntityRepository;
import com.amazingenergy.saitamadomain.customer.domain.attribute.CustomerOption;
import com.amazingenergy.saitamadomain.customer.domain.attribute.CustomerOptionSet;
import com.amazingenergy.saitamadomain.customer.domain.attribute.CustomerOptionValue;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import com.amazingenergy.saitamadomain.reference.domain.Language;

import java.util.List;
import java.util.UUID;

public interface CustomerOptionSetRepository extends EntityRepository<UUID, CustomerOptionSet> {
    List<CustomerOptionSet> listByStore(MerchantStore store, Language language);

    List<CustomerOptionSet> listByOption(CustomerOption option, MerchantStore store);

    List<CustomerOptionSet> listByOptionValue(CustomerOptionValue optionValue, MerchantStore store);

}
