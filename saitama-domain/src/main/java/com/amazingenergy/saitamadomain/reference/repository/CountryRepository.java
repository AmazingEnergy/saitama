package com.amazingenergy.saitamadomain.reference.repository;

import com.amazingenergy.core.repository.EntityRepository;
import com.amazingenergy.saitamadomain.reference.domain.Country;
import com.amazingenergy.saitamadomain.common.Description;
import com.amazingenergy.saitamadomain.reference.domain.Language;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface CountryRepository extends EntityRepository<UUID, Country> {
    Optional<Country> getByCode(String code);

    List<Country> getCountries(Language language);

    Map<String, Country> getCountriesMap(Language language);

    List<Country> getCountries(List<String> isoCodes, Language language);

    /**
     * List country - zone objects by language
     */
    List<Country> listCountryZones(Language language);

}
