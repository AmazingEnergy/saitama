package com.amazingenergy.saitamadomain.order.repository;

import com.amazingenergy.core.repository.EntityRepository;
import com.amazingenergy.saitamadomain.order.domain.orderproduct.OrderProductDownload;

import java.util.List;
import java.util.UUID;

public interface OrderProductDownloadRepository extends EntityRepository<UUID, OrderProductDownload> {
    /**
     * List {@link OrderProductDownload} by order id
     */
    List<OrderProductDownload> getByOrderId(Long orderId);
}
