package com.amazingenergy.saitamadomain.catalog.product.domain.attribute;

import com.amazingenergy.core.domain.Entity;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductOption extends Entity<UUID, ProductOption> {
    private String code;
    private int sortOrder;
    private ProductOptionType type;
    private Set<ProductOptionDescription> descriptions = new HashSet<>();
    private MerchantStore merchantStore;
    private boolean readOnly;

    public ProductOption() {
        super(UUID.randomUUID());
    }
}
