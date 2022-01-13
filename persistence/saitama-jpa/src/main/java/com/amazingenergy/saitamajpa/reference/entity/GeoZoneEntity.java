package com.amazingenergy.saitamajpa.reference.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "GeoZone")
public class GeoZoneEntity {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.TABLE,
            generator = "TableGen")
    @TableGenerator(name = "TableGen",
            table = "SmSequencer",
            pkColumnName = "SeqName",
            valueColumnName = "SeqCount",
            pkColumnValue = "GeoZoneSeqNextVal")
    private UUID id;

    @OneToMany(mappedBy = "geoZone", cascade = CascadeType.ALL)
    private List<DescriptionEntity> descriptions = new ArrayList<DescriptionEntity>();

    @OneToMany(mappedBy = "geoZone", targetEntity = CountryEntity.class)
    private List<CountryEntity> countries = new ArrayList<>();

    @Column(name = "Name")
    private String name;

    @Column(name = "Code")
    private String code;
}
