package com.amazingenergy.saitamadomain.catalog.product.domain.review;

import com.amazingenergy.core.domain.AggregateRoot;
import com.amazingenergy.core.domain.AuditSection;
import com.amazingenergy.saitamadomain.catalog.product.domain.Product;
import com.amazingenergy.saitamadomain.customer.domain.Customer;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductReview extends AggregateRoot<UUID, ProductReview> {
    private double reviewRating;
    private long reviewRead;
    private int status;
    private Date reviewDate;
    private Set<ProductReviewDescription> descriptions = new HashSet<>();
    private AuditSection auditSection;
    private Customer customer;
    private Product product;

    public ProductReview() {
        super(UUID.randomUUID());
    }
}
