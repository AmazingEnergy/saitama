package com.amazingenergy.saitamadomain.shoppingcart.domain;

import com.amazingenergy.core.domain.AggregateRoot;
import com.amazingenergy.core.domain.AuditSection;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class ShoppingCart extends AggregateRoot<UUID, ShoppingCart> {
    /**
     * Will be used to fetch shopping cart model from the controller
     * this is a unique code that should be attributed from the client (UI)
     */
    private String shoppingCartCode;
    private Set<ShoppingCartItem> lineItems = new HashSet<>();
    private MerchantStore merchantStore;
    private UUID customerId;
    private UUID orderId;
    private String ipAddress;
    private String promoCode;
    private Date promoAdded;
    private boolean obsolete = false;//when all items are obsolete
    private AuditSection auditSection;


    public ShoppingCart() {
        super(UUID.randomUUID());
    }
}
