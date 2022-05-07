package com.amazingenergy.saitamajpa.tax.entity;

import com.amazingenergy.corejpa.audit.AuditListener;
import com.amazingenergy.corejpa.audit.Auditable;
import com.amazingenergy.corejpa.audit.EmbeddableAuditSection;
import com.amazingenergy.saitamajpa.merchant.entity.MerchantStoreEntity;
import com.amazingenergy.saitamajpa.reference.entity.CountryEntity;
import com.amazingenergy.saitamajpa.reference.entity.ZoneEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(value = AuditListener.class)
@Table(name = "TaxRate",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"Code", "MerchantId"})})
public class TaxRateEntity implements Auditable {
    @Id
    @Column(name = "Id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Embedded
    private EmbeddableAuditSection auditSection;

    @Column(name = "TaxPriority")
    private Integer taxPriority = 0;

    @Column(name = "Rate", nullable = false, precision = 7, scale = 4)
    private BigDecimal taxRate;

    @Column(name = "Code")
    private String code;

    @Column(name = "Piggyback")
    private boolean piggyback;

    @ManyToOne
    @JoinColumn(name = "TaxClassId", nullable = false)
    private TaxClassEntity taxClass;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MerchantId", nullable = false)
    private MerchantStoreEntity merchantStore;

    @OneToMany(mappedBy = "taxRate", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TaxRateDescriptionEntity> descriptions = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = CountryEntity.class)
    @JoinColumn(name = "CountryId", nullable = false, updatable = true)
    private CountryEntity country;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ZoneId", nullable = true, updatable = true)
    private ZoneEntity zone;

    @Column(name = "StateProvince", length = 100)
    private String stateProvince;

    @ManyToOne
    @JoinColumn(name = "ParentId")
    private TaxRateEntity parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<TaxRateEntity> taxRates = new ArrayList<>();
}
