package com.amazingenergy.saitamadomain.reference.repository;

import com.amazingenergy.core.repository.EntityRepository;
import com.amazingenergy.saitamadomain.reference.domain.Country;
import com.amazingenergy.saitamadomain.common.Description;
import com.amazingenergy.saitamadomain.reference.domain.Language;
import com.amazingenergy.saitamadomain.reference.domain.Zone;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface ZoneService extends EntityRepository<UUID, Zone> {
    Zone getByCode(String code);

    void addDescription(Zone zone, Description description);

    List<Zone> getZones(Country country, Language language);

    Map<String, Zone> getZones(Language language);

    List<Zone> getZones(String countryCode, Language language);
}
