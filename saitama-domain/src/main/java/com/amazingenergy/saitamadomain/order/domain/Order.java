package com.amazingenergy.saitamadomain.order.domain;

import com.amazingenergy.core.domain.AggregateRoot;
import com.amazingenergy.saitamadomain.customer.domain.Billing;
import com.amazingenergy.saitamadomain.customer.domain.Customer;
import com.amazingenergy.saitamadomain.customer.domain.Delivery;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import com.amazingenergy.saitamadomain.order.domain.attribute.OrderAttribute;
import com.amazingenergy.saitamadomain.order.domain.orderproduct.OrderProduct;
import com.amazingenergy.saitamadomain.payment.domain.PaymentType;
import lombok.*;

import java.math.BigDecimal;
import java.util.*;

@Data
@EqualsAndHashCode(callSuper = true)
public class Order extends AggregateRoot<UUID, Order> {
    private OrderStatus status;
    private Date lastModified;
    private Customer customer;
    private String customerEmail;
    private Date datePurchased;
    //used for an order payable on multiple installment
    private Date orderDateFinished;
    //What was the exchange rate
    private BigDecimal currencyValue = new BigDecimal(1);//default 1-1
    private BigDecimal total;
    private String ipAddress;
    private String shoppingCartCode;
    private OrderChannel channel;
    private OrderType orderType = OrderType.ORDER;
    private PaymentType paymentType;
    private String paymentModuleCode;
    private String shippingModuleCode;
    private Boolean customerAgreement = false;
    private Boolean confirmedAddress = false;
    private Delivery delivery = null;
    private Billing billing = null;
    private CreditCard creditCard = null;
    private Currency currency;
    private Locale locale;
    private MerchantStore merchant;
    private Set<OrderProduct> orderProducts = new LinkedHashSet<>();
    private Set<OrderTotal> orderTotal = new LinkedHashSet<>();
    private Set<OrderStatusHistory> orderHistory = new LinkedHashSet<>();
    private Set<OrderAttribute> orderAttributes = new LinkedHashSet<>();


    public Order() {
        super(UUID.randomUUID());
    }
}
