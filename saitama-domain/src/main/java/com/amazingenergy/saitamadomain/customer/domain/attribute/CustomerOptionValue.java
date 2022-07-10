package com.amazingenergy.saitamadomain.customer.domain.attribute;

import com.amazingenergy.core.domain.Entity;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class CustomerOptionValue extends Entity<UUID, CustomerOptionValue> {
    private String code;
    private String image;
    private Set<CustomerOptionValueDescription> descriptions = new HashSet<>();
    private int sortOrder = 0;
    private MerchantStore merchantStore;

    public CustomerOptionValue() {
        super(UUID.randomUUID());
    }
}
