package com.amazingenergy.saitamajpa.catalog.product.entity.availability;

import com.amazingenergy.corejpa.audit.Auditable;
import com.amazingenergy.corejpa.audit.EmbeddableAuditSection;
import com.amazingenergy.saitamajpa.catalog.product.entity.ProductEntity;
import com.amazingenergy.saitamajpa.catalog.product.entity.price.ProductPriceEntity;
import com.amazingenergy.saitamajpa.merchant.entity.MerchantStoreEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ProductAvailability")
public class ProductAvailabilityEntity implements Auditable {
    @Id
    @Column(name = "Id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)

    private UUID id;
    @Embedded
    private EmbeddableAuditSection auditSection;

    @ManyToOne(targetEntity = ProductEntity.class)
    @JoinColumn(name = "ProductEntity", nullable = false)
    private ProductEntity product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MerchantId", nullable = true)
    private MerchantStoreEntity merchantStore;

    @Column(name = "Sku", nullable = true)
    private String sku;

    @Embedded
    private EmbeddableProductDimensions dimensions;

    @Column(name = "Quantity")
    private Integer productQuantity = 0;

    @Temporal(TemporalType.DATE)
    @Column(name = "DateAvailable")
    private Date productDateAvailable;

    @Column(name = "Region")
    private String region;

    @Column(name = "RegionVariant")
    private String regionVariant;

    @Column(name = "Owner")
    private String owner;

    @Column(name = "Status")
    private boolean productStatus;

    @Column(name = "FreeShipping")
    private boolean productIsAlwaysFreeShipping;

    @Column(name = "Available")
    private Boolean available;

    @Column(name = "QuantityOrderMin")
    private Integer productQuantityOrderMin;

    @Column(name = "QuantityOrderMax")
    private Integer productQuantityOrderMax;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "productAvailability", cascade = CascadeType.ALL)
    private Set<ProductPriceEntity> prices = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH})
    @JoinTable(name = "AvailabilityVariation",
            joinColumns = {@JoinColumn(name = "AvailabilityId", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "VariationId", nullable = false, updatable = false)}
    )
    @Cascade({
            org.hibernate.annotations.CascadeType.DETACH,
            org.hibernate.annotations.CascadeType.LOCK,
            org.hibernate.annotations.CascadeType.REFRESH,
            org.hibernate.annotations.CascadeType.REPLICATE

    })
    private Set<ProductVariationEntity> variations = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "productAvailability", cascade = CascadeType.ALL)
    private Set<ProductVariationImageEntity> images = new HashSet<>();
}
