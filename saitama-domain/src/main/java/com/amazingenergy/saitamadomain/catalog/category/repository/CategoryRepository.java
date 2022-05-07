package com.amazingenergy.saitamadomain.catalog.category.repository;

import com.amazingenergy.core.repository.AggregateRootRepository;
import com.amazingenergy.saitamadomain.catalog.category.domain.Category;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import com.amazingenergy.saitamadomain.reference.domain.Language;

import java.util.List;
import java.util.UUID;

public interface CategoryRepository extends AggregateRootRepository<UUID, Category> {
    List<Category> getListByLineage(MerchantStore store, String lineage);

    List<Category> listBySeUrl(MerchantStore store, String seUrl);

    List<Category> listByParent(Category category);

    List<Category> listByStoreAndParent(MerchantStore store, Category category);

    List<Category> getByName(MerchantStore store, String name, Language language);

    List<Category> listByStore(MerchantStore store);

    Category getByCode(MerchantStore store, String code);

    List<Category> listByStore(MerchantStore store, Language language);

    void saveOrUpdate(Category category);

    List<Category> getListByDepth(MerchantStore store, int depth);

    Category getById(UUID id, UUID merchantId);

    Category getById(UUID categoryId, UUID merchantId, UUID languageId);

    /**
     * Get root categories by store for a given language
     */
    List<Category> getListByDepth(MerchantStore store, int depth, Language language);

    /**
     * Returns category hierarchy filter by featured
     */
    List<Category> getListByDepthFilterByFeatured(MerchantStore store, int depth, Language language);

    List<Category> getListByLineage(String storeCode, String lineage);

    Category getByCode(String storeCode, String code);

    Category getById(MerchantStore store, UUID id);

    Category getBySeUrl(MerchantStore store, String seUrl);

    List<Category> listByParent(Category category, Language language);

    Category getOneByLanguage(UUID categoryId, Language language);

    /**
     * Returns a list by category containing the category code and the number of products
     * 1->obj[0] = 1
     * obj[1] = 150
     * 2->obj[0] = 2
     * obj[1] = 35
     * ...
     */
    List<Object[]> countProductsByCategories(MerchantStore store, List<UUID> categoryIds);

    /**
     * Returns a list of Category by category code for a given language
     */
    List<Category> listByCodes(MerchantStore store, List<String> codes, Language language);

    /**
     * List of Category by id
     */
    List<Category> listByIds(MerchantStore store, List<UUID> ids, Language language);

    int count(MerchantStore store);
}
