package com.amazingenergy.saitamadomain.shoppingcart.service;

import com.amazingenergy.saitamadomain.customer.domain.Customer;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import com.amazingenergy.saitamadomain.order.domain.OrderTotalSummary;
import com.amazingenergy.saitamadomain.reference.domain.Language;
import com.amazingenergy.saitamadomain.shoppingcart.domain.ShoppingCart;

/**
 * Interface declaring various methods used to calculate {@link ShoppingCart}
 * object details.
 */
public interface ShoppingCartCalculationService {
    /**
     * Method which will be used to calculate price for each line items as well
     * Total and Sub-total for {@link ShoppingCart}.
     */
    OrderTotalSummary calculate(final ShoppingCart cartModel, final Customer customer,
                                final MerchantStore store, final Language language);

    /**
     * Method which will be used to calculate price for each line items as well
     * Total and Sub-total for {@link ShoppingCart}.
     */
    OrderTotalSummary calculate(final ShoppingCart cartModel, final MerchantStore store, final Language language);
}
