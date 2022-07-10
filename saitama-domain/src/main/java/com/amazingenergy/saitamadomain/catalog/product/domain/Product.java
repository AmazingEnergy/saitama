package com.amazingenergy.saitamadomain.catalog.product.domain;

import com.amazingenergy.core.domain.AggregateRoot;
import com.amazingenergy.core.domain.AuditSection;
import com.amazingenergy.saitamadomain.catalog.category.domain.Category;
import com.amazingenergy.saitamadomain.catalog.product.domain.attribute.ProductAttribute;
import com.amazingenergy.saitamadomain.catalog.product.domain.image.ProductImage;
import com.amazingenergy.saitamadomain.catalog.product.domain.manufacturer.Manufacturer;
import com.amazingenergy.saitamadomain.catalog.product.domain.price.FinalPrice;
import com.amazingenergy.saitamadomain.catalog.product.domain.availability.ProductAvailability;
import com.amazingenergy.saitamadomain.catalog.product.domain.price.ProductPrice;
import com.amazingenergy.saitamadomain.catalog.product.domain.relationship.ProductRelationship;
import com.amazingenergy.saitamadomain.catalog.product.domain.type.ProductType;
import com.amazingenergy.saitamadomain.seedwork.constant.Constants;
import com.amazingenergy.saitamadomain.customer.domain.Customer;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import com.amazingenergy.saitamadomain.tax.domain.TaxClass;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(callSuper = true)
public class Product extends AggregateRoot<UUID, Product> {

    public static final String FAIL_TO_CALCULATE_FINAL_PRICE = "Fail to calculate final price of Product {}";

    private String sku;
    private String refSku;
    private TaxClass taxClass;
    private ProductType type;
    private MerchantStore merchantStore;
    private Manufacturer manufacturer;
    private AuditSection auditSection;

    private Set<Category> categories;
    private Set<ProductDescription> descriptions = new HashSet<>();
    private Set<ProductAvailability> availabilities = new HashSet<>();
    private Set<ProductAttribute> attributes = new HashSet<>();
    private Set<ProductImage> images = new HashSet<>();
    private Set<ProductRelationship> relationships = new HashSet<>();

    private boolean available = true;
    private boolean preOrder = false;
    private boolean productVirtual = false;
    private boolean productShippable = false;
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

    public Product() {
        super(UUID.randomUUID());
    }

    public Optional<ProductDescription> getProductDescription() {
        return descriptions.stream().findFirst();
    }

    public Optional<ProductImage> getProductImage() {
        return images.stream().filter(ProductImage::isDefaultImage).findFirst();
    }

    public BigDecimal getDefaultPriceWithoutDiscount() {
        if (availabilities == null)
            return BigDecimal.ZERO;
        var defaultPriceOpt = availabilities.stream()
                .flatMap(pa -> pa.getPrices().stream()).collect(Collectors.toList())
                .stream().filter(ProductPrice::isDefaultPrice).findFirst();
        return defaultPriceOpt.map(ProductPrice::getAmount)
                .orElseGet(() -> BigDecimal.ZERO);
    }

    public BigDecimal getSumOfAttributePrices() {
        if (attributes == null)
            return BigDecimal.ZERO;
        return attributes.stream().filter(a -> a.getPrice() != null && a.getPrice().doubleValue() > 0)
                .map(ProductAttribute::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Optional<FinalPrice> calculateFinalPrice() {
        if(availabilities == null || availabilities.size() == 0)
            return Optional.empty();

        var availabilitiesByRegion = availabilities.stream()
                .filter(a -> !StringUtils.isEmpty(a.getRegion()) && a.getRegion().equals(Constants.ALL_REGIONS)) // REL 2.1 accept a region
                .flatMap(pa -> pa.getPrices().stream()).collect(Collectors.toList());

        var finalPrices = availabilitiesByRegion.stream().map(FinalPrice::new).collect(Collectors.toList());

        var defaultFinalPriceOpt = finalPrices.stream().filter(FinalPrice::isDefaultPrice).findFirst();
        if (defaultFinalPriceOpt.isEmpty())
            return finalPrices.stream().findFirst();

        var finalPrice = defaultFinalPriceOpt.get();
        finalPrice.setAdditionalPrices(finalPrices.stream().filter(Predicate.not(FinalPrice::isDefaultPrice)).collect(Collectors.toList()));
        return Optional.of(finalPrice);
    }
}
