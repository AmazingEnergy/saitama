package com.amazingenergy.saitamadomain.shoppingcart.domain;

import com.amazingenergy.core.domain.AuditSection;
import com.amazingenergy.core.domain.Entity;
import com.amazingenergy.saitamadomain.catalog.product.domain.attribute.ProductAttribute;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class ShoppingCartAttributeItem extends Entity<UUID, ShoppingCartAttributeItem> {
    private UUID productAttributeId;
    private ProductAttribute productAttribute;
    private ShoppingCartItem shoppingCartItem;
    private AuditSection auditSection;

    public ShoppingCartAttributeItem(ShoppingCartItem shoppingCartItem, ProductAttribute productAttribute) {
        super(UUID.randomUUID());
        this.shoppingCartItem = shoppingCartItem;
        this.productAttribute = productAttribute;
        this.productAttributeId = productAttribute.getId();
    }

    public ShoppingCartAttributeItem(ShoppingCartItem shoppingCartItem, UUID productAttributeId) {
        super(UUID.randomUUID());
        this.shoppingCartItem = shoppingCartItem;
        this.productAttributeId = productAttributeId;
    }

    public ShoppingCartAttributeItem() {
        super(UUID.randomUUID());
    }
}
