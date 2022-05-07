package com.amazingenergy.saitamadomain.tax.repository;

import com.amazingenergy.core.repository.EntityRepository;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import com.amazingenergy.saitamadomain.tax.domain.TaxClass;

import java.util.List;
import java.util.UUID;

public interface TaxClassRepository extends EntityRepository<UUID, TaxClass> {
    List<TaxClass> listByStore(MerchantStore store);

    TaxClass getByCode(String code);

    TaxClass getByCode(String code, MerchantStore store);

    boolean exists(String code, MerchantStore store);

    TaxClass saveOrUpdate(TaxClass taxClass);
}
