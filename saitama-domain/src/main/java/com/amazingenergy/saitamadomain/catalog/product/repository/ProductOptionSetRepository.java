package com.amazingenergy.saitamadomain.catalog.product.repository;

import com.amazingenergy.core.repository.EntityRepository;
import com.amazingenergy.saitamadomain.catalog.product.domain.attribute.ProductOptionSet;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import com.amazingenergy.saitamadomain.reference.domain.Language;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductOptionSetRepository extends EntityRepository<UUID, ProductOptionSet> {
    List<ProductOptionSet> listByStore(MerchantStore store, Language language);

    Optional<ProductOptionSet> getById(MerchantStore store, UUID optionSetId, Language lang);

    Optional<ProductOptionSet> getCode(MerchantStore store, String code);

    List<ProductOptionSet> getByProductType(UUID productTypeId, MerchantStore store, Language lang);
}
