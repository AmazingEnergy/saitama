package com.amazingenergy.saitamadomain.order.repository;

import com.amazingenergy.core.repository.AggregateRootRepository;
import com.amazingenergy.saitamadomain.customer.domain.Customer;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import com.amazingenergy.saitamadomain.order.domain.Order;
import com.amazingenergy.saitamadomain.order.domain.OrderStatusHistory;
import com.amazingenergy.saitamadomain.order.domain.OrderSummary;
import com.amazingenergy.saitamadomain.order.domain.OrderTotalSummary;
import com.amazingenergy.saitamadomain.payment.domain.Payment;
import com.amazingenergy.saitamadomain.payment.domain.Transaction;
import com.amazingenergy.saitamadomain.reference.domain.Language;
import com.amazingenergy.saitamadomain.shoppingcart.domain.ShoppingCart;
import com.amazingenergy.saitamadomain.shoppingcart.domain.ShoppingCartItem;

import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface OrderRepository extends AggregateRootRepository<UUID, Order> {
    void addOrderStatusHistory(Order order, OrderStatusHistory history);

    /**
     * Can be used to calculates the final prices of all items contained in checkout page
     */
    OrderTotalSummary calculateOrderTotal(OrderSummary orderSummary, Customer customer,
                                          MerchantStore store, Language language);

    /**
     * Can be used to calculates the final prices of all items contained in a ShoppingCart
     */
    OrderTotalSummary caculateOrderTotal(OrderSummary orderSummary,
                                         MerchantStore store, Language language);


    /**
     * Can be used to calculates the final prices of all items contained in checkout page
     */
    OrderTotalSummary calculateShoppingCartTotal(final ShoppingCart shoppingCart, final Customer customer,
                                                 final MerchantStore store, final Language language);

    /**
     * Can be used to calculates the final prices of all items contained in a ShoppingCart
     */
    OrderTotalSummary calculateShoppingCartTotal(final ShoppingCart shoppingCart,
                                                 final MerchantStore store, final Language language);

    ByteArrayOutputStream generateInvoice(MerchantStore store, Order order, Language language);

    Order getOrder(Long id, MerchantStore store);


    void saveOrUpdate(Order order);

    Order processOrder(Order order, Customer customer,
                       List<ShoppingCartItem> items, OrderTotalSummary summary,
                       Payment payment, MerchantStore store);

    Order processOrder(Order order, Customer customer,
                       List<ShoppingCartItem> items, OrderTotalSummary summary,
                       Payment payment, Transaction transaction, MerchantStore store);

    /**
     * Determines if an Order has download files
     */
    boolean hasDownloadFiles(Order order);

    /**
     * List all orders that have been pre-authorized but not captured
     */
    List<Order> getCapturableOrders(MerchantStore store, Date startDate, Date endDate);
}
