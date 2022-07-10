package com.amazingenergy.saitamajpa.catalog.product.repository;

import com.amazingenergy.saitamajpa.catalog.product.entity.manufacturer.ManufacturerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaManufacturerRepository extends JpaRepository<ManufacturerEntity, UUID> {
    @Query(value = "select m from ManufacturerEntity m " +
            "left join m.descriptions md " +
            "inner join m.merchantStore ms " +
            "where ms.id=?1 and md.language.id=?2 and (?3 is null or md.name like %?3%)",
            countQuery = "select count(m) from ManufacturerEntity m " +
                    "left join m.descriptions md " +
                    "inner join m.merchantStore ms " +
                    "where ms.id=?1 and md.language.id=?2 and (?3 is null or md.name like %?3%)")
    Page<ManufacturerEntity> findByStore(UUID storeId, UUID languageId, String name, Pageable pageable);

    @Query(value = "select m from ManufacturerEntity m " +
            "left join m.descriptions md " +
            "inner join m.merchantStore ms " +
            "where ms.id=?1 and (?2 is null or md.name like %?2%)",
            countQuery = "select count(m) from ManufacturerEntity m " +
                    "left join m.descriptions md " +
                    "inner join m.merchantStore ms " +
                    "where ms.id=?1 and (?2 is null or md.name like %?2%)")
    Page<ManufacturerEntity> findByStore(UUID storeId, String name, Pageable pageable);

    @Query("select count(distinct p) from ProductEntity as p where p.manufacturer.id=?1")
    Long countByProduct(UUID manufacturerId);

    @Query("select m from ManufacturerEntity m " +
            "left join fetch m.descriptions md " +
            "join fetch m.merchantStore ms " +
            "where ms.id=?1 and md.language.id=?2")
    List<ManufacturerEntity> findByStoreAndLanguage(UUID storeId, UUID languageId);

    @Query("select m from ManufacturerEntity m " +
            "left join fetch  m.descriptions md " +
            "join fetch m.merchantStore ms " +
            "where m.id=?1")
    ManufacturerEntity findOne(UUID id);

    @Query("select m from ManufacturerEntity m " +
            "left join fetch m.descriptions md " +
            "join fetch m.merchantStore ms " +
            "where ms.id=?1")
    List<ManufacturerEntity> findByStore(UUID storeId);

    @Query("select m from ManufacturerEntity m " +
            "join fetch m.descriptions md " +
            "join fetch m.merchantStore ms " +
            "join fetch md.language mdl " +
            "where ms.id=?1 and mdl.id=?2 and (?3 is null or md.name like %?3%)")
        //@Query("select m from Manufacturer m join fetch m.descriptions md join fetch m.merchantStore ms join fetch md.language mdl where ms.id=?1 and mdl.id=?2")
        //@Query("select m from Manufacturer m left join m.descriptions md join fetch m.merchantStore ms where ms.id=?1")
    List<ManufacturerEntity> findByStore(UUID storeId, UUID languageId, String name);


    @Query("select distinct manufacturer from ProductEntity as p " +
            "join p.manufacturer manufacturer " +
            "join manufacturer.descriptions md " +
            "join p.categories categs " +
            "where categs.id in (?1) and md.language.id=?2")
    List<ManufacturerEntity> findByCategoriesAndLanguage(List<UUID> categoryIds, UUID languageId);

    @Query("select m from ManufacturerEntity m " +
            "left join m.descriptions md " +
            "join fetch m.merchantStore ms " +
            "where m.code=?1 and ms.id=?2")
    Optional<ManufacturerEntity> findByCodeAndMerchantStore(String code, UUID storeId);

    @Query("select count(distinct m) from ManufacturerEntity as m where m.merchantStore.id=?1")
    int count(UUID storeId);

    @Query(value = "select distinct manufacturer from ProductEntity as p "
            + "join p.manufacturer manufacturer "
            + "left join manufacturer.descriptions pmd "
            + "join fetch manufacturer.merchantStore pms "
            + "join p.categories pc "
            + "where pms.id = ?1 "
            + "and pc.id IN (select c.id from CategoryEntity c where c.lineage like %?2% and pmd.language.id = ?3)")
    List<ManufacturerEntity> findByProductInCategoryId(UUID storeId, String lineage, UUID languageId);
}
