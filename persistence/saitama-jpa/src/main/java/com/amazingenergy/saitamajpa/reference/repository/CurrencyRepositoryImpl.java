package com.amazingenergy.saitamajpa.reference.repository;

import com.amazingenergy.corejpa.repository.BaseRepositoryImpl;
import com.amazingenergy.saitamadomain.reference.domain.Currency;
import com.amazingenergy.saitamadomain.reference.repository.CurrencyRepository;
import com.amazingenergy.saitamajpa.reference.ReferenceMapper;
import com.amazingenergy.saitamajpa.reference.entity.CurrencyEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class CurrencyRepositoryImpl extends BaseRepositoryImpl<UUID, Currency, CurrencyEntity> implements CurrencyRepository {
    private final JpaCurrencyRepository jpaRepository;
    private final ReferenceMapper mapper;

    public CurrencyRepositoryImpl(JpaCurrencyRepository jpaRepository, ReferenceMapper mapper) {
        super(jpaRepository);
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Optional<Currency> getByCode(String code) {
        return jpaRepository.getByCode(code).map(this::from);
    }

    @Override
    public CurrencyEntity to(Currency source) {
        return mapper.to(source);
    }

    @Override
    public Currency from(CurrencyEntity destination) {
        return mapper.from(destination);
    }
}
