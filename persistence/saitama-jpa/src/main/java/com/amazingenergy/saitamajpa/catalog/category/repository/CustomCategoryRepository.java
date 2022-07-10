package com.amazingenergy.saitamajpa.catalog.category.repository;

import com.amazingenergy.saitamajpa.catalog.category.entity.CategoryEntity;
import com.amazingenergy.saitamajpa.merchant.entity.MerchantStoreEntity;

import java.util.List;
import java.util.UUID;

public interface CustomCategoryRepository {
    List<Object[]> countProductsByCategories(MerchantStoreEntity store, List<UUID> categoryIds);

    List<CategoryEntity> listByStoreAndParent(MerchantStoreEntity store, CategoryEntity category);
}
