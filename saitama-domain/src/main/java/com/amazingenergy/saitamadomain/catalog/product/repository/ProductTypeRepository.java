package com.amazingenergy.saitamadomain.catalog.product.repository;

import com.amazingenergy.core.repository.EntityRepository;
import com.amazingenergy.saitamadomain.catalog.product.domain.type.ProductType;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import com.amazingenergy.saitamadomain.reference.domain.Language;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductTypeRepository extends EntityRepository<UUID, ProductType> {
    Optional<ProductType> getProductType(String productTypeCode);

    Optional<ProductType> getByCode(String code, MerchantStore store, Language language);

    Optional<ProductType> getById(UUID id, MerchantStore store, Language language);

    Optional<ProductType> getById(UUID id, MerchantStore store);

    List<ProductType> listProductTypes(List<UUID> ids, MerchantStore store, Language language);
}
