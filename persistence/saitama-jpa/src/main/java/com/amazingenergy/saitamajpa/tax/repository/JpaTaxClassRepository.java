package com.amazingenergy.saitamajpa.tax.repository;

import com.amazingenergy.saitamajpa.tax.entity.TaxClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaTaxClassRepository extends JpaRepository<TaxClassEntity, UUID> {
    @Query("select t from TaxClassEntity t left join fetch t.merchantStore tm where tm.id=?1")
    List<TaxClassEntity> findByStore(UUID storeId);

    @Query("select t from TaxClassEntity t left join fetch t.merchantStore tm where t.code=?1")
    Optional<TaxClassEntity> findByCode(String code);

    @Query("select t from TaxClassEntity t left join fetch t.merchantStore tm where tm.id=?2 and t.code=?1")
    Optional<TaxClassEntity> findByStoreAndCode(String code, UUID storeId);
}
