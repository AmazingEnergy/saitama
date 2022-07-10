package com.amazingenergy.saitamajpa.customer.repository;

import com.amazingenergy.saitamajpa.customer.entity.attribute.CustomerOptionSetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaCustomerOptionSetRepository extends JpaRepository<CustomerOptionSetEntity, UUID> {
    @Query("select c from CustomerOptionSetEntity c " +
            "join fetch c.option co " +
            "join fetch c.optionValue cov " +
            "join fetch co.merchantStore com " +
            "left join fetch co.descriptions cod " +
            "left join fetch cov.descriptions covd " +
            "where c.id = ?1")
    Optional<CustomerOptionSetEntity> findOne(UUID id);

    @Query("select c from CustomerOptionSetEntity c " +
            "join fetch c.option co " +
            "join fetch c.optionValue cov " +
            "join fetch co.merchantStore com " +
            "left join fetch co.descriptions cod " +
            "left join fetch cov.descriptions covd " +
            "where com.id = ?1 and co.id = ?2")
    List<CustomerOptionSetEntity> findByOptionId(UUID merchantStoreId, UUID id);

    @Query("select c from CustomerOptionSetEntity c " +
            "join fetch c.option co " +
            "join fetch c.optionValue cov " +
            "join fetch co.merchantStore com " +
            "left join fetch co.descriptions cod " +
            "left join fetch cov.descriptions covd " +
            "where com.id = ?1 and cov.id = ?2")
    List<CustomerOptionSetEntity> findByOptionValueId(UUID merchantStoreId, UUID id);

    @Query("select c from CustomerOptionSetEntity c " +
            "join fetch c.option co " +
            "join fetch c.optionValue cov " +
            "join fetch co.merchantStore com " +
            "left join fetch co.descriptions cod " +
            "left join fetch cov.descriptions covd " +
            "where com.id = ?1 " +
            "and cod.language.id = ?2 " +
            "and covd.language.id = ?2 " +
            "order by c.sortOrder asc")
    List<CustomerOptionSetEntity> findByStore(UUID merchantStoreId, UUID languageId);
}
