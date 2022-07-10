package com.amazingenergy.saitamadomain.tax.domain;

import com.amazingenergy.core.domain.Entity;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class TaxClass extends Entity<UUID, TaxClass> {
    public final static String DEFAULT_TAX_CLASS = "DEFAULT";

    private String code;
    private String title;
    private MerchantStore merchantStore;

    public TaxClass() {
        super(UUID.randomUUID());
    }

    public TaxClass(String code) {
        super(UUID.randomUUID());
        this.code = code;
        this.title = code;
    }
}
