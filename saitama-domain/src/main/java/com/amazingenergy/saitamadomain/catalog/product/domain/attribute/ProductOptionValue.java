package com.amazingenergy.saitamadomain.catalog.product.domain.attribute;

import com.amazingenergy.core.domain.Entity;
import com.amazingenergy.saitamadomain.common.Description;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductOptionValue extends Entity<UUID, ProductOptionValue> {
    private String code;
    private int sortOrder;
    private String image;
    private boolean displayOnly = false;
    private Set<ProductOptionValueDescription> descriptions = new HashSet<>();
    private MerchantStore merchantStore;

    public ProductOptionValue() {
        super(UUID.randomUUID());
    }
}
