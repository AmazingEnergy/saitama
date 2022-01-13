package com.amazingenergy.saitamadomain.reference.domain;

import com.amazingenergy.core.domain.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class GeoZone extends Entity<UUID, GeoZone> {
    private String code;
    private String name;
    private List<Description> descriptions = new ArrayList<>();
    private List<Country> countries = new ArrayList<>();

    private GeoZone() {
        super(UUID.randomUUID());
    }

    public GeoZone(UUID id, String code, String name, List<Description> descriptions, List<Country> countries) {
        super(id);
        this.code = code;
        this.name = name;
        this.descriptions = descriptions;
        this.countries = countries;
    }
}
