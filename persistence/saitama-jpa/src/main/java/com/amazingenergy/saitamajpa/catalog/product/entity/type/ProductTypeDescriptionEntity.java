package com.amazingenergy.saitamajpa.catalog.product.entity.type;

import com.amazingenergy.saitamajpa.common.DescriptionEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ProductTypeDescription",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"ProductTypeId", "LanguageId"})})
public class ProductTypeDescriptionEntity extends DescriptionEntity {
    @ManyToOne(targetEntity = ProductTypeEntity.class)
    @JoinColumn(name = "ProductTypeId", nullable = false)
    private ProductTypeEntity productType;
}
