package com.amazingenergy.saitamajpa.tax.repository;

import com.amazingenergy.corejpa.repository.BaseRepositoryImpl;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import com.amazingenergy.saitamadomain.reference.domain.Country;
import com.amazingenergy.saitamadomain.reference.domain.Language;
import com.amazingenergy.saitamadomain.reference.domain.Zone;
import com.amazingenergy.saitamadomain.tax.domain.TaxClass;
import com.amazingenergy.saitamadomain.tax.domain.TaxRate;
import com.amazingenergy.saitamadomain.tax.repository.TaxRateRepository;
import com.amazingenergy.saitamajpa.tax.TaxMapper;
import com.amazingenergy.saitamajpa.tax.entity.TaxRateEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.amazingenergy.saitamadomain.seedwork.constant.UUID.emptyUUID;

@Repository
public class TaxRateRepositoryImpl extends BaseRepositoryImpl<UUID, TaxRate, TaxRateEntity> implements TaxRateRepository {
    private final JpaTaxRateRepository jpaRepository;
    private final TaxMapper mapper;

    public TaxRateRepositoryImpl(JpaTaxRateRepository jpaRepository, TaxMapper mapper) {
        super(jpaRepository);
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public List<TaxRate> listByStore(MerchantStore store) {
        return jpaRepository.findByStore(store.getId())
                .stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public List<TaxRate> listByStore(MerchantStore store, Language language) {
        return jpaRepository.findByStoreAndLanguage(store.getId(), language.getId())
                .stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public List<TaxRate> listByCountryZoneAndTaxClass(Country country, Zone zone, TaxClass taxClass, MerchantStore store, Language language) {
        return jpaRepository.findByMerchantAndZoneAndCountryAndLanguage(store.getId(), zone.getId(), country.getId(), language.getId())
                .stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public List<TaxRate> listByCountryStateProvinceAndTaxClass(Country country, String stateProvince, TaxClass taxClass, MerchantStore store, Language language) {
        return jpaRepository.findByMerchantAndProvinceAndCountryAndLanguage(store.getId(), stateProvince, country.getId(), language.getId())
                .stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public Optional<TaxRate> getByCode(String code, MerchantStore store) {
        return jpaRepository.findByStoreAndCode(code, store.getId()).map(this::from);
    }

    @Override
    public Optional<TaxRate> getById(UUID id, MerchantStore store) {
        return jpaRepository.findByStoreAndId(id, store.getId()).map(this::from);
    }

    @Override
    public TaxRate saveOrUpdate(TaxRate taxRate) {
        if(taxRate.getId() != null && !Objects.equals(taxRate.getId(), emptyUUID()))
            super.update(taxRate);
        else
            taxRate = super.save(taxRate);
        return taxRate;
    }

    @Override
    public TaxRateEntity to(TaxRate source) {
        return mapper.to(source);
    }

    @Override
    public TaxRate from(TaxRateEntity destination) {
        return mapper.from(destination);
    }
}
