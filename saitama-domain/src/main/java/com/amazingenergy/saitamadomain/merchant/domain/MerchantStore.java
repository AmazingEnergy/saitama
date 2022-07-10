package com.amazingenergy.saitamadomain.merchant.domain;

import com.amazingenergy.core.domain.AggregateRoot;
import com.amazingenergy.core.domain.AuditSection;
import com.amazingenergy.saitamadomain.reference.domain.Country;
import com.amazingenergy.saitamadomain.reference.domain.Currency;
import com.amazingenergy.saitamadomain.reference.domain.Language;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.*;


@Data
@EqualsAndHashCode(callSuper = true)
public class MerchantStore extends AggregateRoot<UUID, MerchantStore> {
    public final static String DEFAULT_STORE = "DEFAULT";

    private String code;
    private String storeName;
    private String phone;
    private String email;
    private String address;
    private String city;
    private String stateProvince;
    private String postalCode;
    private Country country;
    private String weightUnitCode = MeasureUnit.LB.name();
    private String seizeUnitCode = MeasureUnit.IN.name();
    private Date inBusinessSince = new Date();
    private boolean retailer = false;
    private Currency currency;
    private Language defaultLanguage;
    private List<Language> languages = new ArrayList<>();
    private String storeTemplate;
    private String invoiceTemplate;
    private String domainName;
    private String continueShoppingUrl;
    private String storeLogo;
    private boolean useCache = false;
    private boolean currencyFormatNational;
    private MerchantStore parent;
    private Set<MerchantStore> stores = new HashSet<>();
    private AuditSection auditSection;

    public MerchantStore() {
        super(UUID.randomUUID());
    }

    public MerchantStore(UUID id, String code, String name) {
        super(id);
        this.code = code;
        this.storeName = name;

    }

    public MerchantStore(UUID id, String code, String name, String storeEmailAddress) {
        super(id);
        this.code = code;
        this.storeName = name;
        this.email = storeEmailAddress;
    }
}
