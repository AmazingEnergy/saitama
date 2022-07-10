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
@Table(name = "GeoZoneDescription", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "GeoZoneId",
                "LanguageId"
        })
})
public class GeoZoneDescriptionEntity extends DescriptionEntity {
    @ManyToOne(targetEntity = GeoZoneEntity.class)
    @JoinColumn(name = "GeoZoneId")
    private GeoZoneEntity geoZone;
}
