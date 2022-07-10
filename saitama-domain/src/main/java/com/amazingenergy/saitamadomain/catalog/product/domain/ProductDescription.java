package com.amazingenergy.saitamadomain.catalog.product.domain;

import com.amazingenergy.saitamadomain.common.Description;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductDescription extends Description {
    private String productHighlight;
    private String productExternalDl;
    private String seUrl;
    private String metaTagTitle;
    private String metaTagKeywords;
    private String metaTagDescription;
}
