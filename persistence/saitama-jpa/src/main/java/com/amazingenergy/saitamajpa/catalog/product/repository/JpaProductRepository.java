package com.amazingenergy.saitamajpa.catalog.product.repository;

import com.amazingenergy.saitamajpa.catalog.product.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface JpaProductRepository extends JpaRepository<ProductEntity, UUID>, CustomProductRepository {

    @Query(value = "select p from ProductEntity as p " +
            "join fetch p.merchantStore pm " +
            "join fetch p.availabilities pa " +
            "join fetch p.descriptions pd " +
            // prices
            "left join fetch pa.prices pap " +
            "left join fetch pap.descriptions papd " +
            // categories
            "left join fetch p.categories categs " +
            "left join fetch categs.descriptions categsd " +
            // images
            "left join fetch p.images images " +
            // options
            "left join fetch p.attributes pattr " +
            "left join fetch pattr.option po " +
            "left join fetch po.descriptions pod " +
            "left join fetch pattr.optionValue pov " +
            "left join fetch pov.descriptions povd " +
            // relationship
            "left join fetch p.relationships pr" +
            // others
            "left join fetch p.manufacturer manuf " +
            "left join fetch manuf.descriptions manufd " +
            "left join fetch p.type type " +
            "left join fetch p.taxClass tx " +
            // rental
            "left join fetch p.owner owner " +
            // filter
            "where p.merchantStore.id=?1 " +
            "and categs.id in (?2) " +
            "and pa.region in (?5) " +
            "and pd.language.id=?3" +
            "and p.available=true and p.dateAvailable<=?4",
            countQuery = "select count(p) from ProductEntity as p " +
                    "inner join p.descriptions pd " +
                    "inner join p.availabilities pa " +
                    "INNER JOIN p.categories categs " +
                    "where p.merchantStore.id=?1 " +
                    "and categs.id in (?2) " +
                    "and pa.region in (?5) " +
                    "and pd.language.id=?3" +
                    "and p.available=true and p.dateAvailable<=?4")
    Page<ProductEntity> getProductsForLocale(UUID storeId, Set<UUID> categoryIds, UUID languageId, Date dateAvailable, List<String> regions, Pageable pageable);

    @Query("select distinct p from ProductEntity as p " +
            "join fetch p.merchantStore m " +
            "where p.id=?1")
    Optional<ProductEntity> getProductWithOnlyMerchantStoreById(UUID productId);

    @Query("select distinct p from ProductEntity as p " +
            "join fetch p.availabilities pa " +
            "join fetch p.descriptions pd " +
            "join fetch p.merchantStore pm " +
            // prices
            "left join fetch pa.prices pap " +
            "left join fetch pap.descriptions papd " +
            // categories
            "left join fetch p.categories categs " +
            "left join fetch categs.descriptions categsd " +
            // images
            "left join fetch p.images images " +
            // options
            "left join fetch p.attributes pattr " +
            "left join fetch pattr.option po " +
            "left join fetch po.descriptions pod " +
            "left join fetch pattr.optionValue pov " +
            "left join fetch pov.descriptions povd " +
            // relationship
            "left join fetch p.relationships pr " +
            // others
            "left join fetch p.manufacturer manuf " +
            "left join fetch manuf.descriptions manufd " +
            "left join fetch p.type type " +
            "left join fetch p.taxClass tx " +
            // rental
            "left join fetch p.owner owner " +
            // filter
            "where pa.region in (?4) " +
            "and pd.seUrl=?3 " +
            "and p.available=true and p.dateAvailable<=?2 " +
            "order by pattr.sortOrder")
    Optional<ProductEntity> getByFriendlyUrl(UUID storeId, Date dateAvailable, String seUrl, List<String> regions);

    @Query("select distinct p from ProductEntity as p " +
            "join fetch p.merchantStore pm " +
            "join fetch p.availabilities pa " +
            "join fetch p.descriptions pd " +
            // prices
            "left join fetch pa.prices pap " +
            "left join fetch pap.descriptions papd " +
            // categories
            "left join fetch p.categories categs " +
            "left join fetch categs.descriptions categsd " +
            // images
            "left join fetch p.images images " +
            // options (do not need attributes for listings)
            "left join fetch p.attributes pattr " +
            "left join fetch pattr.option po " +
            "left join fetch po.descriptions pod " +
            "left join fetch pattr.optionValue pov " +
            "left join fetch pov.descriptions povd " +
            // others
            "left join fetch p.manufacturer manuf " +
            "left join fetch p.type type " +
            "left join fetch p.taxClass tx " +
            // rental
            "left join fetch p.owner owner " +
            // filter
            "where categs.id in (?1)")
    List<ProductEntity> getProductsListByCategories(Set<UUID> categoryIds);

    @Query("select distinct p from ProductEntity as p " +
            "join fetch p.merchantStore pm " +
            "join fetch p.availabilities pa " +
            "join fetch p.descriptions pd " +
            // prices
            "left join fetch pa.prices pap " +
            "left join fetch pap.descriptions papd " +
            // categories
            "left join fetch p.categories categs " +
            "left join fetch categs.descriptions categsd " +
            // images
            "left join fetch p.images images " +
            // options (do not need attributes for listings)
            "left join fetch p.attributes pattr " +
            "left join fetch pattr.option po " +
            "left join fetch po.descriptions pod " +
            "left join fetch pattr.optionValue pov " +
            "left join fetch pov.descriptions povd " +
            // others
            "left join fetch p.manufacturer manuf " +
            "left join fetch manuf.descriptions manufd " +
            "left join fetch p.type type " +
            "left join fetch p.taxClass tx " +
            // rental
            "left join fetch p.owner owner " +
            // filter
            "where categs.id in (?1)" +
            "and pd.language.id=?2 and papd.language.id=?2 " +
            "and p.available=true and p.dateAvailable<=?3")
    List<ProductEntity> getProductsListByCategories(Set<UUID> categoryIds, UUID language, Date dateAvailable);

    @Query("select distinct p from ProductEntity as p " +
            "join fetch p.merchantStore pm " +
            "join fetch p.availabilities pa " +
            "join fetch p.descriptions pd " +
            // prices
            "left join fetch pa.prices pap " +
            "left join fetch pap.descriptions papd " +
            // categories
            "left join fetch p.categories categs " +
            "left join fetch categs.descriptions categsd " +
            // images
            "left join fetch p.images images " +
            // options
            "left join fetch p.attributes pattr " +
            "left join fetch pattr.option po " +
            "left join fetch po.descriptions pod " +
            "left join fetch pattr.optionValue pov " +
            "left join fetch pov.descriptions povd " +
            // relationship
            "left join fetch p.relationships pr" +
            // others
            "left join fetch p.manufacturer manuf " +
            "left join fetch manuf.descriptions manufd " +
            "left join fetch p.type type " +
            "left join fetch p.taxClass tx " +
            // rental
            "left join fetch p.owner owner " +
            // filter
            "where categs.id in (?1) " +
            "and p.available=true and p.dateAvailable<=?2")
    List<ProductEntity> getProductsListByIds(Set<UUID> productIds, Date dataAvailable);

    @Query("select distinct p from ProductEntity as p " +
            "join fetch p.merchantStore pm " +
            "join fetch p.availabilities pa " +
            "join fetch p.descriptions pd " +
            // prices
            "left join fetch pa.prices pap " +
            "left join fetch pap.descriptions papd " +
            // categories
            "left join fetch p.categories categs " +
            "left join fetch categs.descriptions categsd " +
            // images
            "left join fetch p.images images " +
            // options
            "left join fetch p.attributes pattr " +
            "left join fetch pattr.option po " +
            "left join fetch po.descriptions pod " +
            "left join fetch pattr.optionValue pov " +
            "left join fetch pov.descriptions povd " +
            // others
            "left join fetch p.manufacturer manuf " +
            "left join fetch manuf.descriptions manufd " +
            "left join fetch p.type type " +
            "left join fetch p.taxClass tx " +
            // rental
            "left join fetch p.owner owner " +
            // filter
            "where tx.id=?1")
    List<ProductEntity> listByTaxClass(UUID taxClassId);

    @Query("select distinct p from ProductEntity as p " +
            "join fetch p.merchantStore pm " +
            "join fetch p.availabilities pa " +
            "join fetch p.descriptions pd " +
            // prices
            "left join fetch pa.prices pap " +
            "left join fetch pap.descriptions papd " +
            // categories
            "left join fetch p.categories categs " +
            "left join fetch categs.descriptions categsd " +
            // images
            "left join fetch p.images images " +
            // options
            "left join fetch p.attributes pattr " +
            "left join fetch pattr.option po " +
            "left join fetch po.descriptions pod " +
            "left join fetch pattr.optionValue pov " +
            "left join fetch pov.descriptions povd " +
            // others
            "left join fetch p.manufacturer manuf " +
            "left join fetch manuf.descriptions manufd " +
            "left join fetch p.type type " +
            "left join fetch p.taxClass tx " +
            // rental
            "left join fetch p.owner owner " +
            // filter
            "where pm.id=?1")
    List<ProductEntity> listByStore(UUID storeId);

    @Query("select distinct p from ProductEntity as p " +
            "join fetch p.availabilities pa " +
            "join fetch p.descriptions pd " +
            "join fetch p.merchantStore pm " +
            // prices
            "left join fetch pa.prices pap " +
            "left join fetch pap.descriptions papd " +
            // categories
            "left join fetch p.categories categs " +
            "left join fetch categs.descriptions categsd " +
            // images
            "left join fetch p.images images " +
            // options
            "left join fetch p.attributes pattr " +
            "left join fetch pattr.option po " +
            "left join fetch po.descriptions pod " +
            "left join fetch pattr.optionValue pov " +
            "left join fetch pov.descriptions povd " +
            // relationship
            "left join fetch p.relationships pr " +
            // others
            "left join fetch p.manufacturer manuf " +
            "left join fetch manuf.descriptions manufd " +
            "left join fetch p.type type " +
            "left join fetch p.taxClass tx " +
            // rental
            "left join fetch p.owner owner " +
            // filter
            "where p.id=?1" +
            "and pa.region in (?4) " +
            "and pd.language.id=?2 and papd.language.id=?2" +
            "and p.available=true and p.dateAvailable<=?3")
    Optional<ProductEntity> getProductForLocale(UUID productId, UUID languageId, Date dateAvailable, List<String> regions);

    @Query("select distinct p from ProductEntity as p " +
            "join fetch p.merchantStore pm " +
            "join fetch p.availabilities pa " +
            "join fetch p.descriptions pd " +
            // prices
            "left join fetch pa.prices pap " +
            "left join fetch pap.descriptions papd " +
            // categories
            "left join fetch p.categories categs " +
            "left join fetch categs.descriptions categsd " +
            // images
            "left join fetch p.images images " +
            // options
            "left join fetch p.attributes pattr " +
            "left join fetch pattr.option po " +
            "left join fetch po.descriptions pod " +
            "left join fetch pattr.optionValue pov " +
            "left join fetch pov.descriptions povd " +
            // relationship
            "left join fetch p.relationships pr" +
            // others
            "left join fetch p.manufacturer manuf " +
            "left join fetch manuf.descriptions manufd " +
            "left join fetch p.type type " +
            "left join fetch p.taxClass tx " +
            // rental
            "left join fetch p.owner owner " +
            // filter
            "where p.id=?1 " +
            "and pm.id in (?2)")
    Optional<ProductEntity> getById(UUID productId, List<UUID> merchantIds);

    @Query("select distinct p from ProductEntity as p " +
            "join fetch p.merchantStore pm " +
            "join fetch p.availabilities pa " +
            "join fetch p.descriptions pd " +
            // prices
            "left join fetch pa.prices pap " +
            "left join fetch pap.descriptions papd " +
            // categories
            "left join fetch p.categories categs " +
            "left join fetch categs.descriptions categsd " +
            // images
            "left join fetch p.images images " +
            // options
            "left join fetch p.attributes pattr " +
            "left join fetch pattr.option po " +
            "left join fetch po.descriptions pod " +
            "left join fetch pattr.optionValue pov " +
            "left join fetch pov.descriptions povd " +
            // relationship
            "left join fetch p.relationships pr" +
            // others
            "left join fetch p.manufacturer manuf " +
            "left join fetch manuf.descriptions manufd " +
            "left join fetch p.type type " +
            "left join fetch p.taxClass tx " +
            // rental
            "left join fetch p.owner owner " +
            // filter
            "where p.sku=?1 and pd.language.id=?2 and papd.language.id=?2")
    Optional<ProductEntity> getByCodeAndLanguage(String productCode, UUID languageId);

    @Query("select distinct p from ProductEntity as p " +
            "join fetch p.merchantStore pm " +
            "join fetch p.availabilities pa " +
            "join fetch p.descriptions pd " +
            // prices
            "left join fetch pa.prices pap " +
            "left join fetch pap.descriptions papd " +
            // categories
            "left join fetch p.categories categs " +
            "left join fetch categs.descriptions categsd " +
            // images
            "left join fetch p.images images " +
            // options
            "left join fetch p.attributes pattr " +
            "left join fetch pattr.option po " +
            "left join fetch po.descriptions pod " +
            "left join fetch pattr.optionValue pov " +
            "left join fetch pov.descriptions povd " +
            // relationship
            "left join fetch p.relationships pr" +
            // others
            "left join fetch p.manufacturer manuf " +
            "left join fetch manuf.descriptions manufd " +
            "left join fetch p.type type " +
            "left join fetch p.taxClass tx " +
            // rental
            "left join fetch p.owner owner " +
            // filter
            "where p.sku=?1 and pm.id=?2")
    Optional<ProductEntity> getByCodeAndStore(String productCode, UUID storeId);
}
