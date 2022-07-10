package com.amazingenergy.saitamajpa.catalog.product.repository;

import com.amazingenergy.corejpa.repository.BaseRepositoryImpl;
import com.amazingenergy.saitamadomain.catalog.product.domain.Product;
import com.amazingenergy.saitamadomain.catalog.product.domain.review.ProductReview;
import com.amazingenergy.saitamadomain.catalog.product.repository.ProductReviewRepository;
import com.amazingenergy.saitamadomain.customer.domain.Customer;
import com.amazingenergy.saitamadomain.reference.domain.Language;
import com.amazingenergy.saitamajpa.catalog.product.ProductMapper;
import com.amazingenergy.saitamajpa.catalog.product.entity.review.ProductReviewEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class ProductReviewRepositoryImpl extends BaseRepositoryImpl<UUID, ProductReview, ProductReviewEntity> implements ProductReviewRepository {

    private final JpaProductReviewRepository jpaRepository;
    private final ProductMapper mapper;

    public ProductReviewRepositoryImpl(JpaProductReviewRepository jpaRepository, ProductMapper mapper) {
        super(jpaRepository);
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public List<ProductReview> getByProduct(Product product) {
        return jpaRepository.findByProduct(product.getId())
                .stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public List<ProductReview> getByProduct(Product product, Language language) {
        return jpaRepository.findByProduct(product.getId(), language.getId())
                .stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public Optional<ProductReview> getByProductAndCustomer(UUID productId, UUID customerId) {
        return jpaRepository.findByProductAndCustomer(productId, customerId).map(this::from);
    }

    @Override
    public List<ProductReview> getByCustomer(Customer customer) {
        return jpaRepository.findByCustomer(customer.getId())
                .stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public List<ProductReview> getByProductNoCustomers(Product product) {
        return jpaRepository.findByProductNoCustomers(product.getId())
                .stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public ProductReviewEntity to(ProductReview source) {
        return mapper.to(source);
    }

    @Override
    public ProductReview from(ProductReviewEntity destination) {
        return mapper.from(destination);
    }
}
