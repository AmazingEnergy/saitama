package com.amazingenergy.saitamajpa.customer.entity;

import com.amazingenergy.corejpa.audit.AuditListener;
import com.amazingenergy.corejpa.audit.Auditable;
import com.amazingenergy.corejpa.audit.EmbeddableAuditSection;
import com.amazingenergy.saitamadomain.reference.domain.Gender;
import com.amazingenergy.saitamajpa.catalog.product.entity.review.ProductReviewEntity;
import com.amazingenergy.saitamajpa.common.EmbeddableBilling;
import com.amazingenergy.saitamajpa.common.EmbeddableDelivery;
import com.amazingenergy.saitamajpa.customer.entity.attribute.CustomerAttributeEntity;
import com.amazingenergy.saitamajpa.merchant.entity.MerchantStoreEntity;
import com.amazingenergy.saitamajpa.reference.entity.LanguageEntity;
import com.amazingenergy.saitamajpa.user.entity.GroupEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(value = AuditListener.class)
@Table(name = "Customer",
        uniqueConstraints = @UniqueConstraint(columnNames = {"MerchantId", "Name"}))
public class CustomerEntity implements Auditable {
    @Id
    @Column(name = "Id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Embedded
    private EmbeddableAuditSection auditSection;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "customer")
    private Set<CustomerAttributeEntity> attributes = new HashSet<>();

    @Column(name = "Gender", length = 1, nullable = true)
    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DateOfBirth")
    private Date dateOfBirth;

    @Column(name = "Email", length = 96, nullable = false)
    private String email;

    @Column(name = "Name", length = 96)
    private String name;// unique username per store

    @Column(name = "Company", length = 100)
    private String company;

    @Column(name = "Anonymous")
    private boolean anonymous;

    @Column(name = "ReviewAvg")
    private BigDecimal customerReviewAvg;

    @Column(name = "ReviewCount")
    private Integer customerReviewCount;

    @Column(name = "Provider")
    private String provider;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = LanguageEntity.class)
    @JoinColumn(name = "LanguageId", nullable = false)
    private LanguageEntity defaultLanguage;

    @OneToMany(mappedBy = "customer", targetEntity = ProductReviewEntity.class)
    private List<ProductReviewEntity> reviews = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MerchantId", nullable = false)
    private MerchantStoreEntity merchantStore;

    @Embedded
    private EmbeddableDelivery delivery = null;

    @Embedded
    private EmbeddableBilling billing = null;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH})
    @JoinTable(name = "CustomerGroup",
            joinColumns = {@JoinColumn(name = "CustomerId", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "GroupId", nullable = false, updatable = false)})
    @Cascade({
            org.hibernate.annotations.CascadeType.DETACH,
            org.hibernate.annotations.CascadeType.LOCK,
            org.hibernate.annotations.CascadeType.REFRESH,
            org.hibernate.annotations.CascadeType.REPLICATE

    })
    private List<GroupEntity> groups = new ArrayList<>();
}
