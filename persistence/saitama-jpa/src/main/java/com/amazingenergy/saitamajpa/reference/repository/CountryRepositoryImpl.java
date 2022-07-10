package com.amazingenergy.saitamajpa.reference.repository;

import com.amazingenergy.corejpa.repository.BaseRepositoryImpl;
import com.amazingenergy.saitamadomain.reference.domain.Country;
import com.amazingenergy.saitamadomain.reference.domain.Language;
import com.amazingenergy.saitamadomain.reference.repository.CountryRepository;
import com.amazingenergy.saitamajpa.reference.ReferenceMapper;
import com.amazingenergy.saitamajpa.reference.entity.CountryEntity;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class CountryRepositoryImpl extends BaseRepositoryImpl<UUID, Country, CountryEntity> implements CountryRepository {

    private final JpaCountryRepository jpaRepository;
    private final ReferenceMapper mapper;

    public CountryRepositoryImpl(JpaCountryRepository jpaRepository, ReferenceMapper mapper) {
        super(jpaRepository);
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    @Cacheable(cacheNames = "countries_language", key = "#language.getId()")
    public List<Country> getCountries(Language language) {
        return jpaRepository.listByLanguage(language.getId())
                .stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    @Cacheable("country")
    public Optional<Country> getByCode(String code) {
        return jpaRepository.findByIsoCode(code).map(this::from);
    }

    @Override
    @Cacheable(cacheNames = "countries", key = "T(String).join(',',isoCodes).concat(language.getId().toString())")
    public List<Country> getCountries(List<String> isoCodes, Language language) {
        return jpaRepository.listCountries(isoCodes, language.getId())
                .stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    @Cacheable(cacheNames = "countries_zones", key = "#language.getId()")
    public List<Country> listCountryZones(Language language) {
        return jpaRepository.listCountryZonesByLanguage(language.getId())
                .stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public CountryEntity to(Country source) {
        return mapper.to(source);
    }

    @Override
    public Country from(CountryEntity destination) {
        return mapper.from(destination);
    }
}
