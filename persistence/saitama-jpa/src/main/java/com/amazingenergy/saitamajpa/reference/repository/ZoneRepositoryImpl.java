package com.amazingenergy.saitamajpa.reference.repository;

import com.amazingenergy.corejpa.repository.BaseRepositoryImpl;
import com.amazingenergy.saitamadomain.reference.domain.Country;
import com.amazingenergy.saitamadomain.reference.domain.Language;
import com.amazingenergy.saitamadomain.reference.domain.Zone;
import com.amazingenergy.saitamadomain.reference.repository.ZoneRepository;
import com.amazingenergy.saitamajpa.reference.ReferenceMapper;
import com.amazingenergy.saitamajpa.reference.entity.ZoneEntity;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class ZoneRepositoryImpl extends BaseRepositoryImpl<UUID, Zone, ZoneEntity> implements ZoneRepository {
    private final JpaZoneRepository jpaRepository;
    private final ReferenceMapper mapper;

    public ZoneRepositoryImpl(JpaZoneRepository jpaRepository, ReferenceMapper mapper) {
        super(jpaRepository);
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    @Cacheable("zone")
    public Optional<Zone> getByCode(String code) {
        return jpaRepository.findByCode(code).map(this::from);
    }

    @Override
    @Cacheable(cacheNames = "zones", key = "#country.getIsoCode().concat('/').concat(language.getId().toString())")
    public List<Zone> getZones(Country country, Language language) {
        return jpaRepository.listByLanguageAndCountry(country.getIsoCode(), language.getId())
                .stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    @Cacheable(cacheNames = "zones_language", key = "#language.getId()")
    public List<Zone> getZones(Language language) {
        return jpaRepository.listByLanguage(language.getId())
                .stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public ZoneEntity to(Zone source) {
        return mapper.to(source);
    }

    @Override
    public Zone from(ZoneEntity destination) {
        return mapper.from(destination);
    }
}
