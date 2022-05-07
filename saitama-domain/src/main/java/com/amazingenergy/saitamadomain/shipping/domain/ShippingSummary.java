package com.amazingenergy.saitamadomain.shipping.domain;

import com.amazingenergy.saitamadomain.common.Delivery;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public class ShippingSummary implements Serializable {
    private BigDecimal shipping;
    private BigDecimal handling;
    private String shippingModule;
    private String shippingOption;
    private String shippingOptionCode;
    private boolean freeShipping;
    private boolean taxOnShipping;
    private boolean shippingQuote;
    private Delivery deliveryAddress;
}
