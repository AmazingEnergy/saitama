package com.amazingenergy.saitamajpa.catalog.product.repository;

import com.amazingenergy.saitamajpa.catalog.product.entity.attribute.ProductOptionValueEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaProductOptionValueRepository extends JpaRepository<ProductOptionValueEntity, UUID> {
    @Query(value = "select distinct p from ProductOptionValueEntity p " +
            "join fetch p.merchantStore pm " +
            "left join fetch p.descriptions pd " +
            "where pm.id = ?1 and (?2 is null or pd.name like %?2%)",
            countQuery = "select count(p) from ProductOptionValueEntity p " +
                    "join p.merchantStore pm " +
                    "left join p.descriptions pd " +
                    "where pm.id = ?1 and (?2 is null or pd.name like %?2%)")
    Page<ProductOptionValueEntity> listOptionValues(UUID storeId, String name, Pageable pageable);

    @Query("select p from ProductOptionValueEntity p " +
            "join fetch p.merchantStore pm " +
            "left join fetch p.descriptions pd " +
            "where p.id = ?1")
    Optional<ProductOptionValueEntity> findOne(UUID id);

    @Query("select p from ProductOptionValueEntity p " +
            "join fetch p.merchantStore pm " +
            "left join fetch p.descriptions pd " +
            "where p.id = ?2  and pm.id = ?1")
    Optional<ProductOptionValueEntity> findOne(UUID storeId, UUID id);

    @Query("select distinct p from ProductOptionValueEntity p " +
            "join fetch p.merchantStore pm " +
            "left join fetch p.descriptions pd " +
            "where pm.id = ?1 and pd.language.id = ?2")
    List<ProductOptionValueEntity> findByStoreId(UUID storeId, UUID languageId);

    @Query("select p from ProductOptionValueEntity p " +
            "join fetch p.merchantStore pm " +
            "left join fetch p.descriptions pd " +
            "where pm.id = ?1 and p.code = ?2")
    Optional<ProductOptionValueEntity> findByCode(UUID storeId, String optionValueCode);

    @Query("select p from ProductOptionValueEntity p " +
            "join fetch p.merchantStore pm " +
            "left join fetch p.descriptions pd " +
            "where pm.id = ?1 and pd.name like %?2% and pd.language.id = ?3")
    List<ProductOptionValueEntity> findByName(UUID storeId, String name, UUID languageId);

    @Query("select distinct p from ProductOptionValueEntity p " +
            "join fetch p.merchantStore pm " +
            "left join fetch p.descriptions pd " +
            "where pm.id = ?1 and pd.language.id = ?2 and p.displayOnly = ?3")
    List<ProductOptionValueEntity> findByReadOnly(UUID storeId, UUID languageId, boolean readOnly);
}
