package com.amazingenergy.saitamadomain.catalog.product.domain;

import com.amazingenergy.core.domain.AggregateRoot;
import com.amazingenergy.core.domain.AuditSection;
import com.amazingenergy.saitamadomain.catalog.category.domain.Category;
import com.amazingenergy.saitamadomain.catalog.product.domain.attribute.ProductAttribute;
import com.amazingenergy.saitamadomain.catalog.product.domain.availability.ProductAvailability;
import com.amazingenergy.saitamadomain.reference.domain.Description;
import com.amazingenergy.saitamadomain.customer.domain.Customer;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import com.amazingenergy.saitamadomain.tax.domain.TaxClass;
import lombok.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class Product extends AggregateRoot<UUID, Product> {

    private String sku;
    private String refSku;
    private TaxClass taxClass;
    private ProductType type;
    private MerchantStore merchantStore;
    private Manufacturer manufacturer;
    private AuditSection auditSection;

    private Set<Category> categories;
    private Set<Description> descriptions = new HashSet<>();
    private Set<ProductAvailability> availabilities = new HashSet<>();
    private Set<ProductAttribute> attributes = new HashSet<>();
    private Set<ProductImage> images = new HashSet<>();
    private Set<ProductRelationship> relationships = new HashSet<>();

    private boolean available = true;
    private boolean preOrder = false;
    private boolean productVirtual = false;
    private boolean productShipable = false;
    private boolean productIsFree = false;
    private BigDecimal productLength;
    private BigDecimal productWidth;
    private BigDecimal productHeight;
    private BigDecimal productWeight;
    private BigDecimal productReviewAvg;
    private int productReviewCount;
    private int productOrdered;
    private int sortOrder = 0;

    private ProductCondition condition;
    private RentalStatus rentalStatus;
    private int rentalDuration;
    private int rentalPeriod;
    private Customer owner;

    private Product() {
        super(UUID.randomUUID());
    }

    public Description getProductDescription() {
        if(this.getDescriptions()!=null && this.getDescriptions().size()>0) {
            return this.getDescriptions().iterator().next();
        }
        return null;
    }

    public ProductImage getProductImage() {
        ProductImage productImage = null;
        if(this.getImages()!=null && this.getImages().size()>0) {
            for(ProductImage image : this.getImages()) {
                productImage = image;
                if(productImage.isDefaultImage()) {
                    break;
                }
            }
        }
        return productImage;
    }
}
