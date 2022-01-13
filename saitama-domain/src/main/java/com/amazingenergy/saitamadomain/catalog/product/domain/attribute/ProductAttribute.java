package com.amazingenergy.saitamadomain.catalog.product.domain.attribute;

import com.amazingenergy.core.domain.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductAttribute extends Entity<UUID, ProductAttribute> {
    private BigDecimal price;
    private int sortOrder;
    private boolean isFree;
    private BigDecimal weight;
    private boolean isDefault = false;
    private boolean isRequired = false;
    private boolean displayOnly = false;
    private boolean discounted = false;
    private ProductOption option;
    private ProductOptionValue optionValue;

    public ProductAttribute() {
        super(UUID.randomUUID());
    }
}
