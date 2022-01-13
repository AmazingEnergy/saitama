package com.amazingenergy.saitamajpa.merchant.entity;

import com.amazingenergy.saitamajpa.reference.entity.CountryEntity;
import com.amazingenergy.saitamajpa.reference.entity.CurrencyEntity;
import com.amazingenergy.saitamajpa.reference.entity.LanguageEntity;
import com.amazingenergy.saitamajpa.reference.entity.ZoneEntity;
import com.amazingenergy.saitamajpa.core.AuditListener;
import com.amazingenergy.saitamajpa.core.Auditable;
import com.amazingenergy.saitamajpa.core.EmbeddableAuditSection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(value = AuditListener.class)
@Table(name = "MerchantStore")
public class MerchantStoreEntity implements Auditable {
    @Id
    @Column(name = "Id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.TABLE,
            generator = "TableGen")
    @TableGenerator(name = "TableGen",
            table = "SmSequencer",
            pkColumnName = "SeqName",
            valueColumnName = "SeqCount",
            pkColumnValue = "StoreSeqNextVal")
    private UUID id;

    @Embedded
    private EmbeddableAuditSection auditSection;

    @ManyToOne
    @JoinColumn(name = "ParentId")
    private MerchantStoreEntity parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.REMOVE)
    private Set<MerchantStoreEntity> stores = new HashSet<>();

    @Column(name = "IsRetailer")
    private Boolean retailer = false;

    @Column(name = "Name", nullable = false, length = 100)
    private String storename;

    @Column(name = "Code", nullable = false, unique = true, length = 100)
    private String code;

    @Column(name = "Phone", length = 50)
    private String phone;

    @Column(name = "Address")
    private String address;

    @Column(name = "City", length = 100)
    private String city;

    @Column(name = "PostalCode", length = 15)
    private String postalCode;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = CountryEntity.class)
    @JoinColumn(name = "CountryId", nullable = false, updatable = true)
    private CountryEntity country;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = ZoneEntity.class)
    @JoinColumn(name = "ZoneId", nullable = true, updatable = true)
    private ZoneEntity zone;

    @Column(name = "StateProvince", length = 100)
    private String stateProvince;

    @Column(name = "WeightUnitCode", length = 5)
    private String weightUnitCode;

    @Column(name = "SeizeUnitCode", length = 5)
    private String seizeUnitCode;

    @Temporal(TemporalType.DATE)
    @Column(name = "InBusinessSince")
    private Date inBusinessSince = new Date();

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = LanguageEntity.class)
    @JoinColumn(name = "LanguageId", nullable = false)
    private LanguageEntity defaultLanguage;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "MerchantLanguage")
    private List<LanguageEntity> languages = new ArrayList<>();

    @Column(name = "UseCache")
    private boolean useCache = false;

    @Column(name = "StoreTemplate", length = 25)
    private String storeTemplate;

    @Column(name = "InvoiceTemplate", length = 25)
    private String invoiceTemplate;

    @Column(name = "DomainName", length = 80)
    private String domainName;

    @Column(name = "ContinueShoppingUrl", length = 150)
    private String continueShoppingUrl;

    @Column(name = "Email", length = 60, nullable = false)
    private String email;

    @Column(name = "StoreLogo", length = 100)
    private String storeLogo;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = CurrencyEntity.class)
    @JoinColumn(name = "CurrencyId", nullable = false)
    private CurrencyEntity currency;

    @Column(name = "CurrencyFormatNational")
    private boolean currencyFormatNational;
}
