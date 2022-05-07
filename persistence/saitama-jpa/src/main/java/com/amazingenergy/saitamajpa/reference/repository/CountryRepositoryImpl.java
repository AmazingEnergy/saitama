package com.amazingenergy.saitamajpa.reference.repository;

import com.amazingenergy.corejpa.repository.BaseRepositoryImpl;
import com.amazingenergy.saitamadomain.common.Description;
import com.amazingenergy.saitamadomain.reference.domain.Country;
import com.amazingenergy.saitamadomain.reference.domain.Language;
import com.amazingenergy.saitamadomain.reference.repository.CountryRepository;
import com.amazingenergy.saitamajpa.reference.ReferenceMapper;
import com.amazingenergy.saitamajpa.reference.entity.CountryEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

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
    public Optional<Country> getByCode(String code) {
        return jpaRepository.findByIsoCode(code).map(this::from);
    }

    @Override
    public List<Country> getCountries(Language language) {
        return null;
    }

    @Override
    public Map<String, Country> getCountriesMap(Language language) {
        return null;
    }

    @Override
    public List<Country> getCountries(List<String> isoCodes, Language language) {
        return null;
    }

    @Override
    public List<Country> listCountryZones(Language language) {
        return null;
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
