package com.amazingenergy.saitamajpa.catalog.product.repository;

import com.amazingenergy.saitamaappservice.catalog.product.model.ProductCriteria;
import com.amazingenergy.saitamajpa.catalog.product.entity.ProductEntity;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface CustomProductRepository {
    Page<ProductEntity> listByStore(UUID storeId, String languageCode, ProductCriteria criteria);
}
