package com.amazingenergy.saitamadomain.order.repository;

import com.amazingenergy.core.repository.Repository;
import com.amazingenergy.saitamadomain.order.domain.Order;

import java.util.UUID;

public interface OrderRepository extends Repository<Order> {
    Iterable<Order> findAll();

    Order findById(UUID id);
}
