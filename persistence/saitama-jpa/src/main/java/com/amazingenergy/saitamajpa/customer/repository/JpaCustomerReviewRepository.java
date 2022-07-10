package com.amazingenergy.saitamajpa.customer.repository;

import com.amazingenergy.saitamajpa.customer.entity.review.CustomerReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaCustomerReviewRepository extends JpaRepository<CustomerReviewEntity, UUID> {
    @Query("select r from CustomerReviewEntity r " +
            "join fetch r.customer rc " +
            "join fetch r.reviewedCustomer rr " +
            "join fetch rc.merchantStore rrm " +
            "left join fetch r.descriptions rd " +
            "where r.id = ?1")
    Optional<CustomerReviewEntity> findOne(UUID id);

    @Query("select distinct r from CustomerReviewEntity r " +
            "join fetch r.customer rc " +
            "join fetch r.reviewedCustomer rr " +
            "join fetch rc.merchantStore rrm " +
            "left join fetch r.descriptions rd where rc.id = ?1")
    List<CustomerReviewEntity> findByReviewer(UUID id);

    @Query("select distinct r from CustomerReviewEntity r " +
            "join fetch r.customer rc " +
            "join fetch r.reviewedCustomer rr " +
            "join fetch rc.merchantStore rrm " +
            "left join fetch r.descriptions rd " +
            "where rr.id = ?1")
    List<CustomerReviewEntity> findByReviewed(UUID id);

    @Query("select distinct r from CustomerReviewEntity r join fetch " +
            "r.customer rc " +
            //"join fetch rc.attributes rca left join " +
            //"fetch rca.customerOption rcao left join fetch rca.customerOptionValue " +
            //"rcav left join fetch rcao.descriptions rcaod left join fetch rcav.descriptions " +
            "join fetch r.reviewedCustomer rr join fetch rc.merchantStore rrm " +
            "left join fetch r.descriptions rd " +
            "where rc.id = ?1 and rr.id = ?2")
    Optional<CustomerReviewEntity> findByReviewerAndReviewed(UUID reviewerId, UUID reviewedId);
}
