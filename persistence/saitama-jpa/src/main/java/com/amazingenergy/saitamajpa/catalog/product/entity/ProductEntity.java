package com.amazingenergy.saitamajpa.catalog.product.entity;

import com.amazingenergy.corejpa.audit.AuditListener;
import com.amazingenergy.corejpa.audit.Auditable;
import com.amazingenergy.corejpa.audit.EmbeddableAuditSection;
import com.amazingenergy.saitamadomain.catalog.product.domain.ProductCondition;
import com.amazingenergy.saitamadomain.catalog.product.domain.RentalStatus;
import com.amazingenergy.saitamajpa.catalog.category.entity.CategoryEntity;
import com.amazingenergy.saitamajpa.catalog.product.entity.attribute.ProductAttributeEntity;
import com.amazingenergy.saitamajpa.catalog.product.entity.availability.ProductAvailabilityEntity;
import com.amazingenergy.saitamajpa.catalog.product.entity.image.ProductImageEntity;
import com.amazingenergy.saitamajpa.catalog.product.entity.manufacturer.ManufacturerEntity;
import com.amazingenergy.saitamajpa.catalog.product.entity.relationship.ProductRelationshipEntity;
import com.amazingenergy.saitamajpa.catalog.product.entity.type.ProductTypeEntity;
import com.amazingenergy.saitamajpa.customer.entity.CustomerEntity;
import com.amazingenergy.saitamajpa.merchant.entity.MerchantStoreEntity;
import com.amazingenergy.saitamajpa.tax.entity.TaxClassEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(value = AuditListener.class)
@Table(name = "Product", uniqueConstraints = @UniqueConstraint(columnNames = {"Id", "Sku"}))
public class ProductEntity implements Auditable {
    @Id
    @Column(name = "Id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Embedded
    private EmbeddableAuditSection auditSection;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "product")
    private Set<ProductDescriptionEntity> descriptions = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "product")
    private Set<ProductAvailabilityEntity> availabilities = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "product")
    private Set<ProductAttributeEntity> attributes = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "product")
    //cascade is set to remove because product save requires logic to create physical image first
    // and then save the image id in the database, cannot be done in cascade
    private Set<ProductImageEntity> images = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "product")
    private Set<ProductRelationshipEntity> relationships = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MerchantId", nullable = false)
    private MerchantStoreEntity merchantStore;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH})
    @JoinTable(name = "ProductCategory",
            joinColumns = {@JoinColumn(name = "ProductId", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "CategoryId", nullable = false, updatable = false)})
    @Cascade({
            org.hibernate.annotations.CascadeType.DETACH,
            org.hibernate.annotations.CascadeType.LOCK,
            org.hibernate.annotations.CascadeType.REFRESH,
            org.hibernate.annotations.CascadeType.REPLICATE

    })
    private Set<CategoryEntity> categories = new HashSet<>();

    @Column(name = "DateAvailable")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAvailable;

    @Column(name = "Available")
    private boolean available;

    @Column(name = "PreOrder")
    private boolean preOrder;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "ManufacturerId", nullable = true)
    private ManufacturerEntity manufacturer;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "ProductTypeId", nullable = true)
    private ProductTypeEntity type;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "TaxClassId", nullable = true)
    private TaxClassEntity taxClass;

    @Column(name = "ProductVirtual")
    private boolean productVirtual;

    @Column(name = "ProductShippable")
    private boolean productShippable;

    @Column(name = "ProductFree")
    private boolean productIsFree;

    @Column(name = "ProductLength")
    private BigDecimal productLength;

    @Column(name = "ProductWidth")
    private BigDecimal productWidth;

    @Column(name = "ProductHeight")
    private BigDecimal productHeight;

    @Column(name = "ProductWeight")
    private BigDecimal productWeight;

    @Column(name = "ReviewAvg")
    private BigDecimal productReviewAvg;

    @Column(name = "ReviewCount")
    private Integer productReviewCount;

    @Column(name = "QuantityOrdered")
    private Integer productOrdered;

    @Column(name = "SortOrder")
    private Integer sortOrder;

    @Column(name = "Sku")
    private String sku;

    @Column(name = "RefSku")
    private String refSku;

    @Column(name = "Cond", nullable = true)
    private ProductCondition condition;

    @Column(name = "RentalStatus", nullable = true)
    private RentalStatus rentalStatus;

    @Column(name = "RentalDuration", nullable = true)
    private Integer rentalDuration;

    @Column(name = "RentalPeriod", nullable = true)
    private Integer rentalPeriod;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CustomerId", nullable = true)
    private CustomerEntity owner;
}
