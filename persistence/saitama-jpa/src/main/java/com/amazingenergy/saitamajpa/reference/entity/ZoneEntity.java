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
@Table(name = "Zone")
public class ZoneEntity {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @OneToMany(mappedBy = "zone", cascade = CascadeType.ALL)
    private List<ZoneDescriptionEntity> descriptions = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CountryId", nullable = false)
    private CountryEntity country;

    @Column(name = "Code", unique = true, nullable = false)
    private String code;
}
