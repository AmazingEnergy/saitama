package com.amazingenergy.saitamadomain.catalog.product.event;

import com.amazingenergy.core.domain.Event;
import com.amazingenergy.saitamadomain.catalog.product.domain.Product;

public class ProductCreatedEvent extends Event<Product> {
    public ProductCreatedEvent(Product product) {
        super(product);
    }
}
