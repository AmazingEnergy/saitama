package com.amazingenergy.saitamadomain.order.domain.attribute;

import com.amazingenergy.core.domain.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class OrderAttribute extends Entity<UUID, OrderAttribute> {
    private String key;
    private String value;

    public OrderAttribute() {
        super(UUID.randomUUID());
    }
}
