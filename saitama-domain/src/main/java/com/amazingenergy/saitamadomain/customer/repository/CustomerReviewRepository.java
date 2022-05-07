package com.amazingenergy.saitamadomain.customer.repository;

import com.amazingenergy.core.repository.EntityRepository;
import com.amazingenergy.saitamadomain.customer.domain.Customer;
import com.amazingenergy.saitamadomain.customer.domain.review.CustomerReview;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerReviewRepository extends EntityRepository<UUID, CustomerReview> {
    /**
     * All reviews created by a given customer
     */
    List<CustomerReview> getByCustomer(Customer customer);

    /**
     * All reviews received by a given customer
     */
    List<CustomerReview> getByReviewedCustomer(Customer customer);

    /**
     * Get a review made by a customer to another customer
     */
    Optional<CustomerReview> getByReviewerAndReviewed(UUID reviewerId, UUID reviewedId);
}
