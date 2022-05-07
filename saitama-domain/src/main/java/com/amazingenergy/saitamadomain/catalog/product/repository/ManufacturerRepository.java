package com.amazingenergy.saitamadomain.catalog.product.repository;

import com.amazingenergy.core.repository.EntityRepository;
import com.amazingenergy.saitamadomain.catalog.category.domain.Category;
import com.amazingenergy.saitamadomain.catalog.product.domain.manufacturer.Manufacturer;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import com.amazingenergy.saitamadomain.common.Description;
import com.amazingenergy.saitamadomain.reference.domain.Language;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ManufacturerRepository extends EntityRepository<UUID, Manufacturer> {
    List<Manufacturer> listByStore(MerchantStore store, Language language);

    List<Manufacturer> listByStore(MerchantStore store);

    Long getCountManufacturerAttachedProducts(Manufacturer manufacturer);

    void delete(Manufacturer manufacturer);

    Optional<Manufacturer> getByCode(MerchantStore store, String code);

    /**
     * List manufacturers by products from a given list of categories
     */
    List<Manufacturer> listByProductsByCategoriesId(MerchantStore store, List<UUID> categoryIds, Language language);

    /**
     * List by product in category lineage
     */
    List<Manufacturer> listByProductsInCategory(MerchantStore store, Category category, Language language);

    int count(MerchantStore store);
}
