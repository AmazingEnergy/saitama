package com.amazingenergy.saitamajpa.catalog.product.entity.attribute;

import com.amazingenergy.saitamajpa.common.DescriptionEntity;

import javax.persistence.*;

@Entity
@Table(name = "ProductOptionValueDescription",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"OptionValueId", "LanguageId"})})
public class ProductOptionValueDescriptionEntity extends DescriptionEntity {
    @ManyToOne(targetEntity = ProductOptionValueEntity.class)
    @JoinColumn(name = "OptionValueId")
    private ProductOptionValueEntity productOptionValue;
}
