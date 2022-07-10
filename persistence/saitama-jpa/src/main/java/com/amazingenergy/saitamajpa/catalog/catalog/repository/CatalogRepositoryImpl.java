package com.amazingenergy.saitamajpa.catalog.catalog.repository;

import com.amazingenergy.corejpa.repository.BaseRepositoryImpl;
import com.amazingenergy.saitamadomain.catalog.catalog.domain.Catalog;
import com.amazingenergy.saitamadomain.catalog.catalog.repository.CatalogRepository;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import com.amazingenergy.saitamajpa.catalog.catalog.CatalogMapper;
import com.amazingenergy.saitamajpa.catalog.catalog.entity.CatalogEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class CatalogRepositoryImpl extends BaseRepositoryImpl<UUID, Catalog, CatalogEntity> implements CatalogRepository {

    private final JpaCatalogRepository jpaRepository;
    private final CatalogMapper mapper;

    public CatalogRepositoryImpl(JpaCatalogRepository jpaRepository, CatalogMapper mapper) {
        super(jpaRepository);
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Optional<Catalog> findById(UUID catalogId, MerchantStore store) {
        return jpaRepository.findById(catalogId, store.getId()).map(this::from);
    }

    @Override
    public Optional<Catalog> findByCode(String code, MerchantStore store) {
        return jpaRepository.findByCode(code, store.getId()).map(this::from);
    }

    @Override
    public boolean existByCode(String code, MerchantStore store) {
        return jpaRepository.existsByCode(code, store.getId());
    }

    @Override
    public CatalogEntity to(Catalog source) {
        return mapper.to(source);
    }

    @Override
    public Catalog from(CatalogEntity destination) {
        return mapper.from(destination);
    }
}
