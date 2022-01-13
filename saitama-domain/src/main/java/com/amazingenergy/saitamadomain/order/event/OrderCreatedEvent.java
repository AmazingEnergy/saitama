package com.amazingenergy.saitamadomain.order.event;

import com.amazingenergy.core.domain.Event;
import com.amazingenergy.saitamadomain.order.domain.Order;

public class OrderCreatedEvent extends Event<Order> {
    public OrderCreatedEvent(Order order) {
        super(order);
    }
}
