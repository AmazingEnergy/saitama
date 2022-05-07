package com.amazingenergy.saitamajpa.reference.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Country")
@Cacheable
public class CountryEntity {
    @Id
    @Column(name="Id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
    private Set<CountryDescriptionEntity> descriptions = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "country")
    private Set<ZoneEntity> zones = new HashSet<>();

    @ManyToOne(targetEntity = GeoZoneEntity.class)
    @JoinColumn(name = "GeoZoneId")
    private GeoZoneEntity geoZone;

    @Column(name = "Supported")
    private boolean supported = true;

    @Column(name = "IsoCode", unique=true, nullable = false)
    private String isoCode;
}
