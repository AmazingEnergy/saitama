package com.amazingenergy.saitamadomain.catalog.catalog.repository;

import com.amazingenergy.core.repository.AggregateRootRepository;
import com.amazingenergy.saitamadomain.catalog.catalog.domain.Catalog;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;

import java.util.Optional;
import java.util.UUID;

public interface CatalogRepository extends AggregateRootRepository<UUID, Catalog> {
    Optional<Catalog> findById(UUID catalogId, MerchantStore store);

    Optional<Catalog> findByCode(String code, MerchantStore store);

    void delete(Catalog catalog);

    boolean existByCode(String code, MerchantStore store);
}
