package com.amazingenergy.saitamadomain.catalog.product.domain.image;

import com.amazingenergy.saitamadomain.common.Description;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductImageDescription extends Description {
    private String altTag;
}
