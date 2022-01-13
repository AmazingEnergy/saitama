package com.amazingenergy.saitamadomain.catalog.product.domain;

import com.amazingenergy.core.domain.AuditSection;
import com.amazingenergy.core.domain.Entity;
import com.amazingenergy.saitamadomain.reference.domain.Description;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductType extends Entity<UUID, ProductType> {
    private String code;
    private boolean allowAddToCart;
    private boolean visible;
    private Set<Description> descriptions = new HashSet<>();
    private AuditSection auditSection;
    private MerchantStore merchantStore;

    public ProductType() {
        super(UUID.randomUUID());
    }
}
