package com.amazingenergy.saitamajpa.catalog.product.repository;

import com.amazingenergy.saitamajpa.catalog.product.entity.availability.ProductVariationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaProductVariationRepository extends JpaRepository<ProductVariationEntity, UUID> {
    @Query(value = "select distinct p from ProductVariationEntity p " +
            "join fetch p.merchantStore pm " +
            "left join fetch p.option po " +
            "left join fetch po.descriptions " +
            "left join fetch p.optionValue pp " +
            "left join fetch pp.descriptions " +
            "where pm.id = ?1 and (?2 is null or p.code like %?2%)",
            countQuery = "select count(p) from ProductVariationEntity p " +
                    "join p.merchantStore pm " +
                    "where pm.id = ?1 and (?2 is null or p.code like %?2%)")
    Page<ProductVariationEntity> list(UUID storeId, String code, Pageable pageable);

    @Query("select distinct p from ProductVariationEntity p "
            + "join fetch p.merchantStore pm "
            + "left join fetch p.option po "
            + "left join fetch po.descriptions pod "
            + "left join fetch p.optionValue pv "
            + "left join fetch pv.descriptions pvd " +
            "where pm.id = ?1 and p.id = ?2 and pod.language.id = ?3")
    Optional<ProductVariationEntity> findOne(UUID storeId, UUID id, UUID languageId);

    @Query("select distinct p from ProductVariationEntity p " +
            "join fetch p.merchantStore pm " +
            "left join fetch p.option po " +
            "left join fetch po.descriptions pod " +
            "left join fetch p.optionValue pv " +
            "left join fetch pv.descriptions pvd " +
            "where p.code = ?1 and pm.id = ?2")
    Optional<ProductVariationEntity> findByCode(String code, UUID storeId);
}
