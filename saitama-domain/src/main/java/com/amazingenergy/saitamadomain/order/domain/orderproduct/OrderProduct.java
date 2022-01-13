package com.amazingenergy.saitamadomain.order.domain.orderproduct;

import com.amazingenergy.core.domain.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class OrderProduct extends Entity<UUID, OrderProduct> {
    private String sku;
    private String productName;
    private int productQuantity;
    private BigDecimal oneTimeCharge;
    private Set<OrderProductAttribute> orderAttributes = new HashSet<OrderProductAttribute>();
    private Set<OrderProductPrice> prices = new HashSet<OrderProductPrice>();
    private Set<OrderProductDownload> downloads = new HashSet<OrderProductDownload>();

    public OrderProduct() {
        super(UUID.randomUUID());
    }
}
