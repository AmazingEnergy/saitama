package com.amazingenergy.saitamadomain.catalog.product.domain.availability;

import com.amazingenergy.core.domain.AuditSection;
import com.amazingenergy.core.domain.Entity;
import com.amazingenergy.saitamadomain.catalog.product.domain.attribute.ProductOption;
import com.amazingenergy.saitamadomain.catalog.product.domain.attribute.ProductOptionValue;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductVariation extends Entity<UUID, ProductVariation> {
    private String code;
    private ProductOption option;
    private ProductOptionValue optionValue;
    private MerchantStore merchantStore;
    private AuditSection auditSection;

    public ProductVariation() {
        super(UUID.randomUUID());
    }
}
