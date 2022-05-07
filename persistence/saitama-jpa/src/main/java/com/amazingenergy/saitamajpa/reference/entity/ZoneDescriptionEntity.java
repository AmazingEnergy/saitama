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
@Table(name = "ZoneDescription", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "ZoneId",
                "LanguageId"
        })
})
public class ZoneDescriptionEntity extends DescriptionEntity {
    @ManyToOne(targetEntity = ZoneEntity.class)
    @JoinColumn(name = "ZoneId", nullable = false)
    private ZoneEntity zone;
}
