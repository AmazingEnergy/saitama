package com.amazingenergy.saitamadomain.order.service;

import com.amazingenergy.saitamadomain.customer.domain.Customer;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import com.amazingenergy.saitamadomain.order.domain.OrderSummary;
import com.amazingenergy.saitamadomain.order.domain.OrderTotal;
import com.amazingenergy.saitamadomain.reference.domain.Language;

import java.util.List;

public interface OrderTotalService {
    /**
     * Contains a list of negative OrderTotal variation
     * that will be shown in the order summary
     */
    List<OrderTotal> findOrderTotalVariation(final OrderSummary summary, final Customer customer, final MerchantStore store, final Language language) throws Exception;
}
