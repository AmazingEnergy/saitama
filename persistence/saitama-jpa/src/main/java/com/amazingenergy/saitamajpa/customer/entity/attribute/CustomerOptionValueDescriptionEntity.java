package com.amazingenergy.saitamajpa.customer.entity.attribute;

import com.amazingenergy.saitamajpa.common.DescriptionEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CustomerOptionValueDescription",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"CustomerOptionValueId", "LanguageId"})})
public class CustomerOptionValueDescriptionEntity extends DescriptionEntity {
    @ManyToOne(targetEntity = CustomerOptionValueEntity.class)
    @JoinColumn(name = "CustomerOptionValueId")
    private CustomerOptionValueEntity customerOptionValue;
}
