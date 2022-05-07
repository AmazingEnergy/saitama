package com.amazingenergy.saitamajpa.customer.repository;

import com.amazingenergy.corejpa.repository.BaseRepositoryImpl;
import com.amazingenergy.saitamadomain.customer.domain.Customer;
import com.amazingenergy.saitamadomain.customer.domain.review.CustomerReview;
import com.amazingenergy.saitamadomain.customer.repository.CustomerReviewRepository;
import com.amazingenergy.saitamajpa.customer.CustomerMapper;
import com.amazingenergy.saitamajpa.customer.entity.review.CustomerReviewEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class CustomerReviewRepositoryImpl extends BaseRepositoryImpl<UUID, CustomerReview, CustomerReviewEntity> implements CustomerReviewRepository {

    private final JpaCustomerReviewRepository jpaRepository;
    private final CustomerMapper mapper;

    public CustomerReviewRepositoryImpl(JpaCustomerReviewRepository jpaRepository, CustomerMapper mapper) {
        super(jpaRepository);
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public List<CustomerReview> getByCustomer(Customer customer) {
        return jpaRepository.findByReviewer(customer.getId())
                .stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public List<CustomerReview> getByReviewedCustomer(Customer customer) {
        return jpaRepository.findByReviewed(customer.getId())
                .stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public Optional<CustomerReview> getByReviewerAndReviewed(UUID reviewerId, UUID reviewedId) {
        return jpaRepository.findByReviewerAndReviewed(reviewerId, reviewedId).map(this::from);
    }

    @Override
    public CustomerReviewEntity to(CustomerReview source) {
        return mapper.to(source);
    }

    @Override
    public CustomerReview from(CustomerReviewEntity destination) {
        return mapper.from(destination);
    }
}
