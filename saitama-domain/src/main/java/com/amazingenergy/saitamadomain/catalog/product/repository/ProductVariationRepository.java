package com.amazingenergy.saitamadomain.catalog.product.repository;

import com.amazingenergy.core.repository.EntityRepository;
import com.amazingenergy.saitamadomain.catalog.product.domain.availability.ProductVariation;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import com.amazingenergy.saitamadomain.reference.domain.Language;

import java.util.Optional;
import java.util.UUID;

public interface ProductVariationRepository extends EntityRepository<UUID, ProductVariation> {
    Optional<ProductVariation> getById(MerchantStore store, UUID id, Language lang);

    Optional<ProductVariation> getByCode(MerchantStore store, String code);
}
