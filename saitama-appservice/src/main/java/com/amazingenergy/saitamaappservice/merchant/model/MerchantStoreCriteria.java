package com.amazingenergy.saitamaappservice.merchant.model;

import com.amazingenergy.core.Criteria;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MerchantStoreCriteria extends Criteria {
    private boolean isRetailers = false;
    private boolean isStores = false;
}
