package com.amazingenergy.saitamadomain.shipping.domain;

import com.amazingenergy.saitamadomain.catalog.product.domain.Product;
import com.amazingenergy.saitamadomain.catalog.product.domain.price.FinalPrice;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShippingProduct {
    private int quantity = 1;
    private Product product;
    private FinalPrice finalPrice;

    public ShippingProduct(Product product) {
        this.product = product;
    }
}
