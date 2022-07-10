package com.amazingenergy.saitamajpa.catalog.product.repository;

import com.amazingenergy.saitamajpa.catalog.product.entity.type.ProductTypeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaProductTypeRepository extends JpaRepository<ProductTypeEntity, UUID> {
    @Query(value = "select distinct p from ProductTypeEntity p " +
            "left join fetch p.descriptions pd " +
            "left join fetch p.merchantStore pm " +
            "where pm.id=?1",
            countQuery = "select count(p) from ProductTypeEntity p " +
                    "left join p.merchantStore pm " +
                    "where pm.id = ?1")
    Page<ProductTypeEntity> listByStore(UUID storeId, Pageable pageable);

    @Query(value = "select p from ProductTypeEntity p " +
            "join fetch p.merchantStore pm " +
            "where p.code=?1")
    Optional<ProductTypeEntity> findByCode(String code);

    @Query(value = "select p from ProductTypeEntity p " +
            "left join fetch p.descriptions pd " +
            "left join fetch p.merchantStore pm " +
            "where p.code=?1 and (pm is null or pm.id=?2)")
    Optional<ProductTypeEntity> findByCode(String code, UUID storeId);

    @Query(value = "select p from ProductTypeEntity p " +
            "left join fetch p.descriptions pd " +
            "left join fetch p.merchantStore pm " +
            "where p.id=?1 and (pm is null or pm.id=?2)")
    Optional<ProductTypeEntity> findById(UUID id, UUID storeId, UUID languageId);

    @Query(value = "select p from ProductTypeEntity p " +
            "left join fetch p.descriptions pd " +
            "left join fetch p.merchantStore pm " +
            "where p.id=?1 and (pm is null or pm.id=?2)")
    Optional<ProductTypeEntity> findById(UUID id, UUID storeId);

    @Query(value = "select p from ProductTypeEntity p " +
            "left join fetch p.descriptions pd " +
            "join fetch p.merchantStore pm " +
            "where p.id in ?1 and (pm is null or pm.id=?2)")
    List<ProductTypeEntity> findByIds(List<UUID> id, UUID storeId, UUID languageId);
}
