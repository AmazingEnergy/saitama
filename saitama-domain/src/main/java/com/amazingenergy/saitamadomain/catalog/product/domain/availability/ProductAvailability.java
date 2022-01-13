package com.amazingenergy.saitamadomain.catalog.product.domain.availability;

import com.amazingenergy.core.domain.AuditSection;
import com.amazingenergy.core.domain.Entity;
import com.amazingenergy.saitamadomain.catalog.product.domain.Product;
import com.amazingenergy.saitamadomain.constants.SchemaConstant;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductAvailability extends Entity<UUID, ProductAvailability> {
    private int productQuantity  = 0;
    private String sku;
    private Date productDateAvailable;
    private String region = SchemaConstant.ALL_REGIONS;
    private String regionVariant;
    private String owner;
    private boolean productStatus = true;
    private boolean productIsAlwaysFreeShipping;
    private boolean available;
    private int productQuantityOrderMin = 0;
    private int productQuantityOrderMax = 0;
    private ProductDimensions dimensions;
    private Set<ProductPrice> prices = new HashSet<>();
    private Set<ProductVariation> variations = new HashSet<>();
    private Set<ProductVariationImage> images = new HashSet<>();
    private AuditSection auditSection;
    private Product product;
    private MerchantStore merchantStore;

    public ProductAvailability() {
        super(UUID.randomUUID());
    }

    public ProductAvailability(Product product, MerchantStore store) {
        super(UUID.randomUUID());
        this.product = product;
        this.merchantStore = store;
    }

    public ProductPrice defaultPrice() {
        for (ProductPrice price : prices) {
            if (price.isDefaultPrice()) {
                return price;
            }
        }
        return new ProductPrice();
    }
}
