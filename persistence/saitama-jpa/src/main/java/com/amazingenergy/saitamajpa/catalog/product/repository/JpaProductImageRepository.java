package com.amazingenergy.saitamajpa.catalog.product.repository;

import com.amazingenergy.saitamajpa.catalog.product.entity.image.ProductImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaProductImageRepository extends JpaRepository<ProductImageEntity, UUID> {
    @Query("select p from ProductImageEntity p " +
            "left join fetch p.descriptions pd " +
            "inner join fetch p.product pp " +
            "inner join fetch pp.merchantStore ppm " +
            "where p.id = ?1")
    Optional<ProductImageEntity> findOne(UUID id);

    @Query("select p from ProductImageEntity p " +
            "left join fetch p.descriptions pd " +
            "inner join fetch p.product pp " +
            "inner join fetch pp.merchantStore ppm " +
            "where pp.id = ?2 and ppm.code = ?3 and p.id = ?1")
    Optional<ProductImageEntity> finById(UUID imageId, UUID productId, String storeCode);
}
