package com.amazingenergy.saitamadomain.customer.domain.attribute;

import com.amazingenergy.core.domain.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class CustomerAttribute extends Entity<UUID, CustomerAttribute> {
    private String textValue;
    private CustomerOption option;
    private CustomerOptionValue optionValue;

    public CustomerAttribute() {
        super(UUID.randomUUID());
    }
}
