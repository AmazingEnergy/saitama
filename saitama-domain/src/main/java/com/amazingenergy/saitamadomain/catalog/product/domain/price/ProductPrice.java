package com.amazingenergy.saitamadomain.catalog.product.domain.price;

import com.amazingenergy.core.domain.Entity;
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
    public final static String DEFAULT_PRICE_CODE = "BASE";

    private String code = DEFAULT_PRICE_CODE;
    private BigDecimal amount = new BigDecimal(0);
    private ProductPriceType type = ProductPriceType.ONE_TIME;
    private boolean defaultPrice;
    private Date specialStartDate = null;
    private Date specialEndDate = null;
    private BigDecimal specialAmount;
    private Set<ProductPriceDescription> descriptions = new HashSet<>();

    public ProductPrice() {
        super(UUID.randomUUID());
    }

    public boolean isDiscount() {
        return isSpecialDateRangeValid() || isSpecialEndDateValid() || hasSpecialAmount();
    }

    public boolean isSpecialDateRangeValid() {
        return specialStartDate != null && specialStartDate.before(new Date())
                && specialEndDate != null && specialEndDate.after(new Date());
    }

    public boolean isSpecialEndDateValid() {
        return specialStartDate == null && specialEndDate != null
                && specialEndDate.after(new Date());
    }

    public boolean hasSpecialAmount() {
        return specialAmount != null && specialAmount.doubleValue() > 0;
    }
}
