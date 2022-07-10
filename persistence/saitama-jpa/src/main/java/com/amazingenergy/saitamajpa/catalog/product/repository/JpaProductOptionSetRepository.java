package com.amazingenergy.saitamajpa.catalog.product.repository;

import com.amazingenergy.saitamajpa.catalog.product.entity.attribute.ProductOptionSetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaProductOptionSetRepository extends JpaRepository<ProductOptionSetEntity, UUID> {
    @Query("select distinct p from ProductOptionSetEntity p " +
            "join fetch p.store pm " +
            "left join fetch p.option po " +
            "left join fetch po.descriptions pod " +
            "left join fetch p.values pv " +
            "left join fetch pv.descriptions pvd " +
            "where pm.id = ?1 and p.id = ?2 and pod.language.id = ?3")
    Optional<ProductOptionSetEntity> findOne(UUID storeId, UUID id, UUID languageId);

    @Query("select distinct p from ProductOptionSetEntity p " +
            "join fetch p.store pm " +
            "left join fetch p.option po " +
            "left join fetch po.descriptions pod " +
            "left join fetch p.values pv " +
            "left join fetch pv.descriptions pvd " +
            "where pm.id = ?1 and pod.language.id = ?2")
    List<ProductOptionSetEntity> findByStore(UUID storeId, UUID languageId);

    @Query("select distinct p from ProductOptionSetEntity p "
            + "join fetch p.store pm left join fetch p.productTypes pt "
            + "left join fetch p.option po "
            + "left join fetch po.descriptions pod "
            + "left join fetch p.values pv "
            + "left join fetch pv.descriptions pvd " +
            "where pt.id= ?1 and pm.id = ?2 and pod.language.id = ?3")
    List<ProductOptionSetEntity> findByProductType(UUID typeId, UUID storeId, UUID languageId);

    @Query("select p from ProductOptionSetEntity p " +
            "join fetch p.store pm " +
            "left join fetch p.option po " +
            "left join fetch po.descriptions pod " +
            "left join fetch p.values pv " +
            "left join fetch pv.descriptions pvd " +
            "where pm.id = ?1 and p.code = ?2")
    Optional<ProductOptionSetEntity> findByCode(UUID storeId, String code);
}
