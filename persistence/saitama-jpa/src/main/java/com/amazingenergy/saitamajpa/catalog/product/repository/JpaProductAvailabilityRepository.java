package com.amazingenergy.saitamajpa.catalog.product.repository;

import com.amazingenergy.saitamajpa.catalog.product.entity.availability.ProductAvailabilityEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaProductAvailabilityRepository extends JpaRepository<ProductAvailabilityEntity, UUID> {
    @Query(value = "select distinct p from ProductAvailabilityEntity p "
            + "left join fetch p.merchantStore pm "
            + "left join fetch p.prices pp "
            + "left join fetch pp.descriptions ppd "
            + "join fetch p.product ppr "
            + "left join fetch ppr.merchantStore pprm "
            + "where ppr.id=?1 "
            + "and pprm.id=?2 "
            + "and (?3 is null or pm.code like %?3%)",
            countQuery = "select  count(p) from ProductAvailabilityEntity p "
                    + "join p.merchantStore pm "
                    + "join p.prices pp "
                    + "join pp.descriptions ppd "
                    + "join p.product ppr "
                    + "join ppr.merchantStore pprm "
                    + "where ppr.id=?1 "
                    + "and pprm.id=?2 "
                    + "and (?3 is null or pm.code like %?3%)")
    Page<ProductAvailabilityEntity> listByStore(UUID productId, UUID storeId, String child, Pageable pageable);

    @Query("select count(distinct p) from ProductAvailabilityEntity as p where p.product.id=?1")
    int count(UUID productId);

    @Query(value = "select distinct p from ProductAvailabilityEntity p "
            + "left join fetch p.merchantStore pm "
            + "left join fetch p.prices pp "
            + "left join fetch pp.descriptions ppd "
            + "join fetch p.product ppr "
            + "join fetch ppr.merchantStore pprm "
            + "where p.id=?1 ")
    Optional<ProductAvailabilityEntity> getDetailById(UUID id);

    @Query(value = "select distinct p from ProductAvailabilityEntity p "
            + "left join fetch p.merchantStore pm "
            + "left join fetch p.prices pp "
            + "left join fetch pp.descriptions ppd "
            + "join fetch p.product ppr "
            + "join fetch ppr.merchantStore pprm "
            + "where p.id=?1 "
            + "and pprm.id=?2")
    Optional<ProductAvailabilityEntity> getById(UUID id, UUID merchantId);

    @Query(value = "select distinct p from ProductAvailabilityEntity p "
            + "left join fetch p.merchantStore pm "
            + "left join fetch p.prices pp "
            + "left join fetch pp.descriptions ppd "
            + "join fetch p.product ppr "
            + "join fetch ppr.merchantStore pprm "
            + "where ppr.id=?1 "
            + "and pm.code=?2")
    Optional<ProductAvailabilityEntity> getByStore(UUID productId, String storeCode);

    @Query(value = "select distinct p from ProductAvailabilityEntity p "
            + "left join fetch p.merchantStore pm "
            + "left join fetch p.prices pp "
            + "left join fetch pp.descriptions ppd "
            + "join fetch p.product ppr "
            + "join fetch ppr.merchantStore pprm "
            + "where ppr.id=?1 "
            + "and p.id=?2")
    Optional<ProductAvailabilityEntity> getByStore(UUID productId, UUID id);
}
