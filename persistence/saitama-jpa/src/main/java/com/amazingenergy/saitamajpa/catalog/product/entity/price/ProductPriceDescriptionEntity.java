package com.amazingenergy.saitamajpa.catalog.product.entity.price;

import com.amazingenergy.saitamajpa.common.DescriptionEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ProductPriceDescription",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"PriceId", "LanguageId"})})
public class ProductPriceDescriptionEntity extends DescriptionEntity {
    @ManyToOne(targetEntity = ProductPriceEntity.class)
    @JoinColumn(name = "PriceId", nullable = false)
    private ProductPriceEntity productPrice;

    @Column(name = "PriceAppender")
    private String priceAppender;
}
