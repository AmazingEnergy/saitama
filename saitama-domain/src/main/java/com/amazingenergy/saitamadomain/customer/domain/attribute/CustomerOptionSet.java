package com.amazingenergy.saitamadomain.customer.domain.attribute;

import com.amazingenergy.core.domain.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class CustomerOptionSet extends Entity<UUID, CustomerOptionSet> {
    private CustomerOption option = null;
    private CustomerOptionValue optionValue = null;
    private int sortOrder = 0;

    public CustomerOptionSet() {
        super(UUID.randomUUID());
    }
}
