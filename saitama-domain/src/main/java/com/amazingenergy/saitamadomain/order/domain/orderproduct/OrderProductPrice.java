package com.amazingenergy.saitamadomain.order.domain.orderproduct;

import com.amazingenergy.core.domain.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class OrderProductPrice extends Entity<UUID, OrderProductPrice> {
    private String name;
    private String code;
    private BigDecimal price;
    private BigDecimal priceSpecial;
    private Date priceSpecialStartDate;
    private Date priceSpecialEndDate;
    private boolean defaultPrice;

    public OrderProductPrice() {
        super(UUID.randomUUID());
    }
}
