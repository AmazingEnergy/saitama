package com.amazingenergy.saitamadomain.order.repository;

import com.amazingenergy.core.repository.EntityRepository;
import com.amazingenergy.saitamadomain.order.domain.Order;
import com.amazingenergy.saitamadomain.order.domain.OrderStatusHistory;

import java.util.List;
import java.util.UUID;

public interface OrderStatusHistoryRepository extends EntityRepository<UUID, OrderStatusHistory> {
    List<OrderStatusHistory> findByOrder(Order order);
}
