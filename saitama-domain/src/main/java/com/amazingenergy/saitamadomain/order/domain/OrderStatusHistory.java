package com.amazingenergy.saitamadomain.order.domain;

import com.amazingenergy.core.domain.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class OrderStatusHistory extends Entity<UUID, OrderStatusHistory> {
    private OrderStatus status;
    private Date dateAdded;
    private int customerNotified;
    private String comments;

    public OrderStatusHistory() {
        super(UUID.randomUUID());
    }
}
