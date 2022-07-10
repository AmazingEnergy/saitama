package com.amazingenergy.saitamadomain.catalog.product.repository;

import com.amazingenergy.core.repository.AggregateRootRepository;
import com.amazingenergy.saitamadomain.catalog.category.domain.Category;
import com.amazingenergy.saitamadomain.catalog.product.domain.Product;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import com.amazingenergy.saitamadomain.reference.domain.Language;
import com.amazingenergy.saitamadomain.tax.domain.TaxClass;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends AggregateRootRepository<UUID, Product> {
    Optional<Product> retrieveById(UUID id);

    Optional<Product> getProductForLocale(UUID productId, Language language, Locale locale);

    List<Product> getProductsForLocale(Category category, Language language, Locale locale);

    List<Product> getProducts(List<UUID> categoryIds);

    List<Product> getProductsByIds(List<UUID> productIds);

    /**
     * Get a product with only MerchantStore object
     */
    Optional<Product> getProductWithOnlyMerchantStoreById(UUID productId);

    List<Product> listByStore(MerchantStore store);

    List<Product> listByTaxClass(TaxClass taxClass);

    List<Product> getProducts(List<UUID> categoryIds, Language language);

    Optional<Product> getBySeUrl(MerchantStore store, String seUrl, Locale locale);

    /**
     * Get a product by sku (code) field  and the language
     */
    Optional<Product> getByCode(String productCode, Language language);

    Optional<Product> getByCode(String productCode, MerchantStore merchant);

    /**
     * Find a product for a specific merchant
     */
    Optional<Product> findOne(UUID id, MerchantStore merchant);
}
