package com.amazingenergy.saitamadomain.order.domain;

import com.amazingenergy.saitamadomain.shipping.domain.ShippingSummary;
import com.amazingenergy.saitamadomain.shoppingcart.domain.ShoppingCartItem;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class OrderSummary implements Serializable {
    private OrderSummaryType orderSummaryType = OrderSummaryType.ORDERTOTAL;
    private ShippingSummary shippingSummary;
    private String promoCode;
    private List<ShoppingCartItem> products = new ArrayList<ShoppingCartItem>();
}
