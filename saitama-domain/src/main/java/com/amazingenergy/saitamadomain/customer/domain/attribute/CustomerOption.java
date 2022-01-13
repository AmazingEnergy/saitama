package com.amazingenergy.saitamadomain.customer.domain.attribute;

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
public class CustomerOption extends Entity<UUID, CustomerOption> {
    private int sortOrder = 0;
    private CustomerOptionType type;
    private String code;
    private boolean active;
    private boolean publicOption;
    private Set<Description> descriptions = new HashSet<>();
    private MerchantStore merchantStore;

    public CustomerOption() {
        super(UUID.randomUUID());
    }
}
