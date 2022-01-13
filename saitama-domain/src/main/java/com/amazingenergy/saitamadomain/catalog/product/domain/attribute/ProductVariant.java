package com.amazingenergy.saitamadomain.catalog.product.domain.attribute;

import com.amazingenergy.core.domain.AuditSection;
import com.amazingenergy.core.domain.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductVariant extends Entity<UUID, ProductVariant> {
    private int productQuantity = 0;
    private ProductAttribute attribute;
    private AuditSection auditSection;

    public ProductVariant() {
        super(UUID.randomUUID());
    }
}
