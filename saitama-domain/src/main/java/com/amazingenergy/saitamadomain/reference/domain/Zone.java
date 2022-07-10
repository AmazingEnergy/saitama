package com.amazingenergy.saitamadomain.reference.domain;

import com.amazingenergy.core.domain.Entity;
import com.amazingenergy.saitamadomain.common.Description;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class Zone extends Entity<UUID, Zone> {
    private String code;
    private String name;
    private List<ZoneDescription> descriptionList = new ArrayList<>();

    private Zone() {
        super(UUID.randomUUID());
    }

    public Zone(UUID id, String code, String name, List<ZoneDescription> descriptionList) {
        super(id);
        this.code = code;
        this.name = name;
        this.descriptionList = descriptionList;
    }
}
