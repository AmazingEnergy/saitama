package com.amazingenergy.saitamajpa.catalog.product.repository;

import com.amazingenergy.saitamajpa.catalog.product.entity.price.ProductPriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaProductPriceRepository extends JpaRepository<ProductPriceEntity, UUID> {
    @Query("select p from ProductPriceEntity p " +
            "left join fetch p.descriptions pd " +
            "inner join fetch p.productAvailability pa " +
            "inner join fetch pa.product pap " +
            "inner join fetch pap.merchantStore papm " +
            "where p.id = ?1")
    Optional<ProductPriceEntity> findOne(UUID id);
}
