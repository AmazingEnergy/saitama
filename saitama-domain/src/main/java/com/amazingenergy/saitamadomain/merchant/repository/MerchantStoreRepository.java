package com.amazingenergy.saitamadomain.merchant.repository;

import com.amazingenergy.core.repository.AggregateRootRepository;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MerchantStoreRepository extends AggregateRootRepository<UUID, MerchantStore> {
    Optional<MerchantStore> getByCode(String code);

    List<MerchantStore> findAllStoreNames(String code);

    boolean existByCode(String code);

    /**
     * Is parent or child and part of a specific group
     */
    boolean isStoreInGroup(String code);
}
