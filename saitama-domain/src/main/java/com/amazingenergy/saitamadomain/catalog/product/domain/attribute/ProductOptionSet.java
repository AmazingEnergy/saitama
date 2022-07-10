package com.amazingenergy.saitamadomain.catalog.product.domain.attribute;

import com.amazingenergy.core.domain.Entity;
import com.amazingenergy.saitamadomain.catalog.product.domain.type.ProductType;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.*;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductOptionSet extends Entity<UUID, ProductOptionSet> {
    private String code;
    private ProductOption option;
    private List<ProductOptionValue> values = new ArrayList<>();
    private Set<ProductType> productTypes = new HashSet<>();
    private MerchantStore merchantStore;
    private boolean displayOnly = false;

    public ProductOptionSet() {
        super(UUID.randomUUID());
    }
}
