package com.amazingenergy.saitamajpa.customer.repository;

import com.amazingenergy.saitamajpa.customer.entity.attribute.CustomerOptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaCustomerOptionRepository extends JpaRepository<CustomerOptionEntity, UUID> {
    @Query("select o from CustomerOptionEntity o " +
            "join fetch o.merchantStore om " +
            "left join fetch o.descriptions od " +
            "where o.id = ?1")
    Optional<CustomerOptionEntity> findOne(UUID id);

    @Query("select o from CustomerOptionEntity o " +
            "join fetch o.merchantStore om " +
            "left join fetch o.descriptions od " +
            "where om.id = ?1 and o.code = ?2")
    Optional<CustomerOptionEntity> findByCode(UUID merchantId, String code);

    @Query("select o from CustomerOptionEntity o " +
            "join fetch o.merchantStore om " +
            "left join fetch o.descriptions od " +
            "where om.id = ?1 and od.language.id = ?2")
    List<CustomerOptionEntity> findByStore(UUID merchantId, UUID languageId);
}
