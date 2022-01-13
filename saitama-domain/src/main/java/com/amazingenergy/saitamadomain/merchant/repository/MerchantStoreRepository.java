package com.amazingenergy.saitamadomain.merchant.repository;

import com.amazingenergy.core.repository.Repository;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;

import java.util.UUID;

public interface MerchantStoreRepository extends Repository<MerchantStore> {
    Iterable<MerchantStore> findAll();
    MerchantStore findById(UUID id);
}
