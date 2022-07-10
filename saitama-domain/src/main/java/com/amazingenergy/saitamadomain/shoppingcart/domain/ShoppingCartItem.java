package com.amazingenergy.saitamadomain.shoppingcart.domain;

import com.amazingenergy.core.domain.AuditSection;
import com.amazingenergy.core.domain.Entity;
import com.amazingenergy.saitamadomain.catalog.product.domain.Product;
import com.amazingenergy.saitamadomain.catalog.product.domain.price.FinalPrice;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class ShoppingCartItem extends Entity<UUID, ShoppingCartItem> {
    private Integer quantity = 0;
    private UUID productId;
    private Set<ShoppingCartAttributeItem> attributes = new HashSet<>();
    private BigDecimal itemPrice;//item final price including all rebates
    private BigDecimal subTotal;//item final price * quantity
    private FinalPrice finalPrice;//contains price details (raw prices)
    private Product product;
    private boolean productVirtual;
    private boolean obsolete = false;
    private AuditSection auditSection;

    public ShoppingCartItem(Product product) {
        super(UUID.randomUUID());
        this.product = product;
        this.productId = product.getId();
        this.quantity = 1;
        this.productVirtual = product.isProductVirtual();
    }

    public ShoppingCartItem() {
        super(UUID.randomUUID());
    }
}
