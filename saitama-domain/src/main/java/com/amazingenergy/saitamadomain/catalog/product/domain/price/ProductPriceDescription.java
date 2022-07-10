package com.amazingenergy.saitamadomain.catalog.product.domain.price;

import com.amazingenergy.saitamadomain.common.Description;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductPriceDescription extends Description {
    private String priceAppender;
}
