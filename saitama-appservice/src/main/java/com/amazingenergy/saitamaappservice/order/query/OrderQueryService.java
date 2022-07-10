package com.amazingenergy.saitamaappservice.order.query;

import com.amazingenergy.saitamaappservice.order.model.OrderCriteria;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import com.amazingenergy.saitamadomain.order.domain.Order;
import org.springframework.data.domain.Page;

public interface OrderQueryService {
    /**
     * For finding orders. Mainly used in the administration tool
     */
    Page<Order> listByStore(MerchantStore store, OrderCriteria criteria);


    /**
     * get all orders. Mainly used in the administration tool
     */
    Page<Order> getOrders(OrderCriteria criteria, MerchantStore store);
}
