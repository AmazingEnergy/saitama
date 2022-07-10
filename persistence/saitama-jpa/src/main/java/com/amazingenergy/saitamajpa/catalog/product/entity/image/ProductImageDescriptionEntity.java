package com.amazingenergy.saitamajpa.catalog.product.entity.image;

import com.amazingenergy.saitamajpa.common.DescriptionEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ProductImageDescription",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"ProductImageId", "LanguageId"})})
public class ProductImageDescriptionEntity extends DescriptionEntity {
    @ManyToOne(targetEntity = ProductImageEntity.class)
    @JoinColumn(name = "ProductImageId", nullable = false)
    private ProductImageEntity productImage;

    @Column(name = "AltTag", length = 100)
    private String altTag;
}
