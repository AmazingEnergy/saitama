package com.amazingenergy.saitamadomain.catalog.product.repository;

import com.amazingenergy.core.repository.EntityRepository;
import com.amazingenergy.saitamadomain.catalog.product.domain.attribute.ProductOptionValue;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import com.amazingenergy.saitamadomain.reference.domain.Language;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductOptionValueRepository extends EntityRepository<UUID, ProductOptionValue> {
    List<ProductOptionValue> getByName(MerchantStore store, String name, Language language);

    List<ProductOptionValue> listByStore(MerchantStore store, Language language);

    List<ProductOptionValue> listByStoreNoReadOnly(MerchantStore store, Language language);

    Optional<ProductOptionValue> getByCode(MerchantStore store, String optionValueCode);

    Optional<ProductOptionValue> getById(MerchantStore store, UUID optionValueId);
}
