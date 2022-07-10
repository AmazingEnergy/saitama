package com.amazingenergy.saitamadomain.catalog.product.repository;

import com.amazingenergy.core.repository.EntityRepository;
import com.amazingenergy.saitamadomain.catalog.product.domain.Product;
import com.amazingenergy.saitamadomain.catalog.product.domain.attribute.ProductAttribute;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import com.amazingenergy.saitamadomain.reference.domain.Language;

import java.util.List;
import java.util.UUID;

public interface ProductAttributeRepository extends EntityRepository<UUID, ProductAttribute> {
    void saveOrUpdate(ProductAttribute productAttribute);

    List<ProductAttribute> getByOptionId(MerchantStore store, UUID id);

    List<ProductAttribute> getByOptionValueId(MerchantStore store, UUID id);

    List<ProductAttribute> getByProductId(MerchantStore store, Product product, Language language);

    List<ProductAttribute> getByProductId(MerchantStore store, Product product);

    List<ProductAttribute> getByAttributeIds(MerchantStore store, Product product, List<UUID> ids);

    List<ProductAttribute> getProductAttributesByCategoryLineage(MerchantStore store, String lineage, Language language);
}
