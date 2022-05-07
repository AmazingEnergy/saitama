package com.amazingenergy.saitamajpa.catalog.product.entity.price;

import com.amazingenergy.saitamadomain.catalog.product.domain.price.ProductPriceType;
import com.amazingenergy.saitamajpa.catalog.product.entity.availability.ProductAvailabilityEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@Table(name = "ProductPrice")
public class ProductPriceEntity {
    @Id
    @Column(name = "Id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "productPrice", cascade = CascadeType.ALL)
    private Set<ProductPriceDescriptionEntity> descriptions = new HashSet<>();

    @Column(name = "Code", nullable = false)
    private String code;

    @Column(name = "Amount", nullable = false)
    private BigDecimal amount = new BigDecimal(0);

    @Column(name = "PriceType", length = 20)
    @Enumerated(value = EnumType.STRING)
    private ProductPriceType productPriceType;

    @Column(name = "DefaultPrice")
    private boolean defaultPrice;

    @Temporal(TemporalType.DATE)
    @Column(name = "SpecialStartDate")
    private Date specialStartDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "SpecialEndDate")
    private Date specialEndDate;

    @Column(name = "SpecialAmount")
    private BigDecimal specialAmount;

    @ManyToOne(targetEntity = ProductAvailabilityEntity.class)
    @JoinColumn(name = "AvailabilityId", nullable = false)
    private ProductAvailabilityEntity productAvailability;
}
