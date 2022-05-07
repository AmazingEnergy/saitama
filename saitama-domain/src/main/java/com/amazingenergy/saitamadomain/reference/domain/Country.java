package com.amazingenergy.saitamadomain.reference.domain;

import com.amazingenergy.core.domain.Entity;
import com.amazingenergy.saitamadomain.common.Description;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class Country extends Entity<UUID, Country> {
    private String isoCode;
    private String name;
    private boolean supported = true;
    private Set<CountryDescription> descriptions = new HashSet<>();
    private Set<Zone> zones = new HashSet<>();

    private Country() {
        super(UUID.randomUUID());
    }

    public Country(UUID id, String isoCode, String name, boolean supported, Set<CountryDescription> descriptions, Set<Zone> zones) {
        super(id);
        this.isoCode = isoCode;
        this.name = name;
        this.supported = supported;
        this.descriptions = descriptions;
        this.zones = zones;
    }
}
