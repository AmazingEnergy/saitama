package com.amazingenergy.saitamajpa.reference;

import com.amazingenergy.saitamadomain.reference.domain.*;
import com.amazingenergy.saitamajpa.reference.entity.*;
import org.mapstruct.Mapper;

@Mapper
public interface ReferenceMapper {
    CountryEntity to(Country source);

    Country from(CountryEntity destination);

    CountryDescriptionEntity to(CountryDescription source);

    CountryDescription from(CountryDescriptionEntity destination);

    CurrencyEntity to(Currency source);

    Currency from(CurrencyEntity destination);

    GeoZoneEntity to(GeoZone source);

    GeoZone from(GeoZoneEntity destination);

    GeoZoneDescriptionEntity to(GeoZoneDescription source);

    GeoZoneDescription from(GeoZoneDescriptionEntity destination);

    LanguageEntity to(Language source);

    Language from(LanguageEntity destination);

    ZoneEntity to(Zone source);

    Zone from(ZoneEntity destination);

    ZoneDescriptionEntity to(ZoneDescription source);

    ZoneDescription from(ZoneDescriptionEntity destination);
}
