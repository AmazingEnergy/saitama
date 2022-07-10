package com.amazingenergy.saitamadomain.catalog.product.repository;

import com.amazingenergy.core.repository.EntityRepository;
import com.amazingenergy.saitamadomain.catalog.product.domain.attribute.ProductOption;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import com.amazingenergy.saitamadomain.reference.domain.Language;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductOptionRepository extends EntityRepository<UUID, ProductOption> {
    List<ProductOption> listByStore(MerchantStore store, Language language);

    List<ProductOption> getByName(MerchantStore store, String name, Language language);

    List<ProductOption> listReadOnly(MerchantStore store, Language language);

    Optional<ProductOption> getByCode(MerchantStore store, String optionCode);

    Optional<ProductOption> getById(MerchantStore store, UUID optionId);
}
