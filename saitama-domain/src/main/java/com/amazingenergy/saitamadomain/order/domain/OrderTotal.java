package com.amazingenergy.saitamadomain.order.domain;

import com.amazingenergy.core.domain.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class OrderTotal extends Entity<UUID, OrderTotal> {
    private String orderTotalCode;//SHIPPING, TAX
    private String title;
    private String text;
    private BigDecimal value;
    private String module;
    private OrderValueType orderValueType = OrderValueType.ONE_TIME;
    private OrderTotalType orderTotalType = null;
    private int sortOrder;

    public OrderTotal() {
        super(UUID.randomUUID());
    }
}
