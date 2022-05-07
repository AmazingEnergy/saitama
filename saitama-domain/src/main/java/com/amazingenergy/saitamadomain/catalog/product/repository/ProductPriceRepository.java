package com.amazingenergy.saitamadomain.catalog.product.repository;

import com.amazingenergy.core.repository.EntityRepository;
import com.amazingenergy.saitamadomain.catalog.product.domain.price.ProductPrice;
import com.amazingenergy.saitamadomain.common.Description;

import java.util.UUID;

public interface ProductPriceRepository extends EntityRepository<UUID, ProductPrice> {
}
