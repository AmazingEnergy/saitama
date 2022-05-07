package com.amazingenergy.saitamaappservice.catalog.product.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AttributeCriteria implements Serializable {
    private String attributeCode;
    private String attributeValue;
}
