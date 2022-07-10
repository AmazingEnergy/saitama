package com.amazingenergy.saitamajpa.reference.entity;

import com.amazingenergy.saitamajpa.common.DescriptionEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CountryDescription", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "CountryId",
                "LanguageId"
        })
})
public class CountryDescriptionEntity extends DescriptionEntity {
    @ManyToOne(targetEntity = CountryEntity.class)
    @JoinColumn(name = "CountryId", nullable = false)
    private CountryEntity country;
}
