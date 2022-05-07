package com.amazingenergy.saitamajpa.customer.repository;

import com.amazingenergy.saitamajpa.customer.entity.attribute.CustomerAttributeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaCustomerAttributeRepository extends JpaRepository<CustomerAttributeEntity, UUID> {
    @Query("select a from CustomerAttributeEntity a " +
            "left join fetch a.option aco " +
            "left join fetch a.optionValue acov " +
            "left join fetch aco.descriptions acod " +
            "left join fetch acov.descriptions acovd " +
            "where a.id = ?1")
    Optional<CustomerAttributeEntity> findOne(UUID id);

    @Query("select a from CustomerAttributeEntity a " +
            "join fetch a.customer ac " +
            "left join fetch a.option aco " +
            "join fetch aco.merchantStore acom " +
            "left join fetch a.optionValue acov " +
            "left join fetch aco.descriptions acod " +
            "left join fetch acov.descriptions acovd " +
            "where acom.id = ?1 and ac.id = ?2 and aco.id = ?3")
    Optional<CustomerAttributeEntity> findByOptionId(UUID merchantId, UUID customerId, UUID id);

    @Query("select a from CustomerAttributeEntity a " +
            "join fetch a.customer ac " +
            "left join fetch a.option aco " +
            "join fetch aco.merchantStore acom " +
            "left join fetch a.optionValue acov " +
            "left join fetch aco.descriptions acod " +
            "left join fetch acov.descriptions acovd " +
            "where acom.id = ?1 and aco.id = ?2")
    List<CustomerAttributeEntity> findByOptionId(UUID merchantId, UUID id);

    @Query("select distinct a from CustomerAttributeEntity a " +
            "join fetch a.customer ac " +
            "left join fetch a.option aco " +
            "join fetch aco.merchantStore acom " +
            "left join fetch a.optionValue acov " +
            "left join fetch aco.descriptions acod " +
            "left join fetch acov.descriptions acovd " +
            "where acom.id = ?1 and ac.id = ?2")
    List<CustomerAttributeEntity> findByCustomerId(UUID merchantId, UUID customerId);

    @Query("select a from CustomerAttributeEntity a " +
            "join fetch a.customer ac " +
            "left join fetch a.option aco " +
            "join fetch aco.merchantStore acom " +
            "left join fetch a.optionValue acov " +
            "left join fetch aco.descriptions acod " +
            "left join fetch acov.descriptions acovd " +
            "where acom.id = ?1 and acov.id = ?2")
    List<CustomerAttributeEntity> findByOptionValueId(UUID merchantId, UUID Id);
}
