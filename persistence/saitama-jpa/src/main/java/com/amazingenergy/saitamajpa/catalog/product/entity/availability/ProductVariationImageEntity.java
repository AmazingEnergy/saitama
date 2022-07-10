package com.amazingenergy.saitamajpa.catalog.product.entity.availability;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ProductionVariationImage")
public class ProductVariationImageEntity {
    @Id
    @Column(name = "Id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "ProductImage")
    private String image;

    @Column(name = "DefaultImage")
    private boolean defaultImage;

    @ManyToOne(targetEntity = ProductAvailabilityEntity.class)
    @JoinColumn(name = "AvailabilityId", nullable = false)
    private ProductAvailabilityEntity productAvailability;
}
