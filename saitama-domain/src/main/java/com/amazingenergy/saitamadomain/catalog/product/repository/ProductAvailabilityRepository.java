package com.amazingenergy.saitamadomain.catalog.product.repository;

import com.amazingenergy.core.repository.EntityRepository;
import com.amazingenergy.saitamadomain.catalog.product.domain.Product;
import com.amazingenergy.saitamadomain.catalog.product.domain.availability.ProductAvailability;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;

import java.util.Optional;
import java.util.UUID;

public interface ProductAvailabilityRepository extends EntityRepository<UUID, ProductAvailability> {
    void saveOrUpdate(ProductAvailability availability);

    Optional<ProductAvailability> getByStore(Product product, MerchantStore store);

    Optional<ProductAvailability> getById(UUID availabilityId, MerchantStore store);

    Optional<ProductAvailability> getByInventoryId(UUID productId, UUID availabilityId, MerchantStore store);

    int count(Product product);
}
