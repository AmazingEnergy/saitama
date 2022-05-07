package com.amazingenergy.saitamaappservice.catalog.product.model;

import com.amazingenergy.core.Criteria;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductCriteria extends Criteria {
    private String productName;
    private List<AttributeCriteria> attributeCriteria;
    private Boolean available = null;
    private List<Long> categoryIds;
    private List<String> availabilities;
    private List<Long> productIds;
    private List<Long> optionValueIds;
    private String status;
    private Long manufacturerId = null;
    private Long ownerId = null;
}
