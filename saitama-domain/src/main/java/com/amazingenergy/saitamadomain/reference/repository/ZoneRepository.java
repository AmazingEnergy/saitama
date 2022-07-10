package com.amazingenergy.saitamadomain.reference.repository;

import com.amazingenergy.core.repository.EntityRepository;
import com.amazingenergy.saitamadomain.reference.domain.Country;
import com.amazingenergy.saitamadomain.common.Description;
import com.amazingenergy.saitamadomain.reference.domain.Language;
import com.amazingenergy.saitamadomain.reference.domain.Zone;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface ZoneRepository extends EntityRepository<UUID, Zone> {
    Optional<Zone> getByCode(String code);

    List<Zone> getZones(Country country, Language language);

    List<Zone> getZones(Language language);
}
