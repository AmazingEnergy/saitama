package com.amazingenergy.saitamadomain.catalog.product.domain.availability;

import com.amazingenergy.core.domain.Entity;
import com.amazingenergy.saitamadomain.reference.domain.Description;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductPrice extends Entity<UUID, ProductPrice> {
    public final static String DEFAULT_PRICE_CODE="BASE";

    private String code = DEFAULT_PRICE_CODE;
    private BigDecimal amount = new BigDecimal(0);
    private ProductPriceType type = ProductPriceType.ONE_TIME;
    private boolean defaultPrice;
    private Date specialStartDate;
    private Date specialEndDate;
    private BigDecimal specialAmount;
    private Set<Description> descriptions = new HashSet<>();

    public ProductPrice() {
        super(UUID.randomUUID());
    }
}
