package com.amazingenergy.saitamadomain.catalog.product.domain.attribute;

import com.amazingenergy.saitamadomain.common.Description;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductOptionDescription extends Description {
    private String productOptionComment;
}
