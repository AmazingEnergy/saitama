package com.amazingenergy.saitamadomain.tax.domain;

import com.amazingenergy.core.domain.AuditSection;
import com.amazingenergy.core.domain.Entity;
import com.amazingenergy.saitamadomain.reference.domain.Country;
import com.amazingenergy.saitamadomain.common.Description;
import com.amazingenergy.saitamadomain.reference.domain.Zone;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class TaxRate extends Entity<UUID, TaxRate> {
    private String code;
    private boolean piggyback;
    private int taxPriority = 0;
    private BigDecimal taxRate;
    private TaxClass taxClass;
    private MerchantStore merchantStore;
    private List<TaxRateDescription> descriptions = new ArrayList<>();
    private Country country;
    private Zone zone;
    private String stateProvince;
    private TaxRate parent;
    private List<TaxRate> taxRates = new ArrayList<TaxRate>();
    private String rateText;
    private AuditSection auditSection;

    public TaxRate() {
        super(UUID.randomUUID());
    }
}
