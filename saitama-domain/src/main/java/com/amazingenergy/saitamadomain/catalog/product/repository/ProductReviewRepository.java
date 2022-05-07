package com.amazingenergy.saitamadomain.catalog.product.repository;

import com.amazingenergy.core.repository.AggregateRootRepository;
import com.amazingenergy.saitamadomain.catalog.product.domain.Product;
import com.amazingenergy.saitamadomain.catalog.product.domain.review.ProductReview;
import com.amazingenergy.saitamadomain.customer.domain.Customer;
import com.amazingenergy.saitamadomain.reference.domain.Language;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductReviewRepository extends AggregateRootRepository<UUID, ProductReview> {
    List<ProductReview> getByCustomer(Customer customer);

    List<ProductReview> getByProduct(Product product);

    List<ProductReview> getByProduct(Product product, Language language);

    Optional<ProductReview> getByProductAndCustomer(UUID productId, UUID customerId);

    List<ProductReview> getByProductNoCustomers(Product product);
}
