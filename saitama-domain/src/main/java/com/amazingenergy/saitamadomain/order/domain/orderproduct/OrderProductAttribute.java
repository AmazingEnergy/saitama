package com.amazingenergy.saitamadomain.order.domain.orderproduct;

import com.amazingenergy.core.domain.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class OrderProductAttribute extends Entity<UUID, OrderProductAttribute> {
    private BigDecimal price;
    private boolean isFree;
    private BigDecimal weight;
    private long productOptionId;
    private long productOptionValueId;
    private String productAttributeName;
    private String productAttributeValueName;

    public OrderProductAttribute() {
        super(UUID.randomUUID());
    }
}
