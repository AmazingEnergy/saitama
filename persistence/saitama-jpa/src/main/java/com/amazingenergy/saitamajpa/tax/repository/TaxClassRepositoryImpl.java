package com.amazingenergy.saitamajpa.tax.repository;

import com.amazingenergy.corejpa.repository.BaseRepositoryImpl;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import com.amazingenergy.saitamadomain.tax.domain.TaxClass;
import com.amazingenergy.saitamadomain.tax.repository.TaxClassRepository;
import com.amazingenergy.saitamajpa.tax.TaxMapper;
import com.amazingenergy.saitamajpa.tax.entity.TaxClassEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.amazingenergy.saitamadomain.seedwork.constant.UUID.emptyUUID;

@Repository
public class TaxClassRepositoryImpl extends BaseRepositoryImpl<UUID, TaxClass, TaxClassEntity> implements TaxClassRepository {
    private final JpaTaxClassRepository jpaRepository;
    private final TaxMapper mapper;

    public TaxClassRepositoryImpl(JpaTaxClassRepository jpaRepository, TaxMapper mapper) {
        super(jpaRepository);
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public List<TaxClass> listByStore(MerchantStore store) {
        return jpaRepository.findByStore(store.getId())
                .stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public Optional<TaxClass> getByCode(String code) {
        return jpaRepository.findByCode(code).map(this::from);
    }

    @Override
    public Optional<TaxClass> getByCode(String code, MerchantStore store) {
        return jpaRepository.findByStoreAndCode(code, store.getId()).map(this::from);
    }

    @Override
    public TaxClass saveOrUpdate(TaxClass taxClass) {
        if(taxClass.getId() != null && !Objects.equals(taxClass.getId(), emptyUUID()))
            super.update(taxClass);
        else
            taxClass = super.save(taxClass);
        return taxClass;
    }

    @Override
    public TaxClassEntity to(TaxClass source) {
        return mapper.to(source);
    }

    @Override
    public TaxClass from(TaxClassEntity destination) {
        return mapper.from(destination);
    }
}
