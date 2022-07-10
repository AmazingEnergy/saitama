package com.amazingenergy.saitamajpa.tax.entity;

import com.amazingenergy.saitamajpa.common.DescriptionEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TaxRateDescription",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"TaxRateId", "LanguageId"})})
public class TaxRateDescriptionEntity extends DescriptionEntity {
    @ManyToOne(targetEntity = TaxRateEntity.class)
    @JoinColumn(name = "TaxRateId")
    private TaxRateEntity taxRate;
}
