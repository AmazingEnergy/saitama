package com.amazingenergy.saitamadomain.catalog.product.domain;

import com.amazingenergy.core.domain.Entity;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductRelationship extends Entity<UUID, ProductRelationship> {
    private String code;
    private Product product;
    private Product relatedProduct;
    private MerchantStore merchantStore;
    private boolean active = true;

    public ProductRelationship() {
        super(UUID.randomUUID());
    }
}
