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
@Table(name = "CustomerOptionDescription",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"CustomerOptionId", "LanguageId"})})
public class CustomerOptionDescriptionEntity extends DescriptionEntity {
    @ManyToOne(targetEntity = CustomerOptionEntity.class)
    @JoinColumn(name = "CustomerOptionId", nullable = false)
    private CustomerOptionEntity customerOption;

    @Column(name = "Comment", length = 4000)
    private String customerOptionComment;
}
