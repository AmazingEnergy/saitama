package com.amazingenergy.saitamadomain.catalog.product.domain;

import com.amazingenergy.core.domain.AuditSection;
import com.amazingenergy.core.domain.Entity;
import com.amazingenergy.saitamadomain.reference.domain.Description;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class Manufacturer extends Entity<UUID, Manufacturer> {
    public static final String DEFAULT_MANUFACTURER = "DEFAULT";

    private String code;
    private String image;
    private int sortOrder;
    private Set<Description> descriptions = new HashSet<>();
    private AuditSection auditSection;
    private MerchantStore merchantStore;

    public Manufacturer() {
        super(UUID.randomUUID());
    }
}
