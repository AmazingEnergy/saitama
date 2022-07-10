package com.amazingenergy.saitamadomain.catalog.product.repository;

import com.amazingenergy.core.repository.EntityRepository;
import com.amazingenergy.saitamadomain.catalog.product.domain.Product;
import com.amazingenergy.saitamadomain.catalog.product.domain.relationship.ProductRelationship;
import com.amazingenergy.saitamadomain.catalog.product.domain.relationship.ProductRelationshipType;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import com.amazingenergy.saitamadomain.reference.domain.Language;

import java.util.List;
import java.util.UUID;

public interface ProductRelationshipRepository extends EntityRepository<UUID, ProductRelationship> {
    /**
     * Get product relationship List for a given type (RELATED, FEATURED...) and language allows
     * to return the product description in the appropriate language
     */
    List<ProductRelationship> getByType(MerchantStore store, Product product, ProductRelationshipType type, Language language);

    /**
     * Find by product and group name
     */
    List<ProductRelationship> getByType(MerchantStore store, Product product, String name);

    /**
     * Get product relationship List for a given type (RELATED, FEATURED...) and a given base product
     */
    List<ProductRelationship> getByType(MerchantStore store, Product product, ProductRelationshipType type);

    /**
     * Get product relationship List for a given type (RELATED, FEATURED...)
     */
    List<ProductRelationship> getByType(MerchantStore store, ProductRelationshipType type);

    List<ProductRelationship> listByProduct(Product product);

    List<ProductRelationship> getByType(MerchantStore store, ProductRelationshipType type, Language language);

    /**
     * Get a list of relationship acting as groups of products
     */
    List<ProductRelationship> getGroups(MerchantStore store);

    void deleteGroup(MerchantStore store, String groupName);

    void deactivateGroup(MerchantStore store, String groupName);

    void activateGroup(MerchantStore store, String groupName);

    List<ProductRelationship> getByGroup(MerchantStore store, String groupName, Language language);
}
