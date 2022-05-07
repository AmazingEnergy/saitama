package com.amazingenergy.saitamajpa.catalog.product.entity.attribute;

import com.amazingenergy.saitamajpa.common.DescriptionEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ProductOptionDescription",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"OptionId", "LanguageId"})})
public class ProductOptionDescriptionEntity extends DescriptionEntity {
    @ManyToOne(targetEntity = ProductOptionEntity.class)
    @JoinColumn(name = "OptionId", nullable = false)
    private ProductOptionEntity productOption;

    @Column(name = "Comment", length = 4000)
    private String productOptionComment;
}
