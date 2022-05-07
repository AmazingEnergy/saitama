package com.amazingenergy.saitamadomain.catalog.product.domain.price;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

@Data
public class FinalPrice implements Serializable {
    private BigDecimal originalPrice;//original price
    private BigDecimal finalPrice;//final price discount or not

    private BigDecimal discountedPrice;//final price if a discount is applied
    private boolean discounted = false;
    private Date discountEndDate = null;
    private int discountPercent = 0;

    private ProductPrice productPrice;
    private boolean defaultPrice;
    private String stringPrice;
    List<FinalPrice> additionalPrices;

    public FinalPrice(ProductPrice productPrice) {
        this.productPrice = productPrice;
        this.defaultPrice = productPrice.isDefaultPrice();
        this.originalPrice = productPrice.getAmount();
        if (productPrice.isDiscount()) {
            this.finalPrice = productPrice.getSpecialAmount();
            this.discountEndDate = productPrice.getSpecialEndDate();

            var arith = productPrice.getSpecialAmount()
                    .divide(productPrice.getAmount(), RoundingMode.HALF_DOWN).doubleValue();
            this.discountPercent = (int) (100 - (arith * 100));
        } else
            this.finalPrice = productPrice.getAmount();

        this.discountedPrice = this.originalPrice.subtract(this.finalPrice);
    }

    public void addAdvance(BigDecimal advancedAmount) {
        this.originalPrice = originalPrice.add(advancedAmount);
        this.finalPrice = finalPrice.add(advancedAmount);
        this.discountedPrice = discountedPrice.add(advancedAmount);
    }
}
