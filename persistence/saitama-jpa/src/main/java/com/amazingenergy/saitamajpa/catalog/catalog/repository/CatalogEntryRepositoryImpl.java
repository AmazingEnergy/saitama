package com.amazingenergy.saitamajpa.catalog.catalog.repository;

import com.amazingenergy.corejpa.repository.BaseRepositoryImpl;
import com.amazingenergy.saitamadomain.catalog.catalog.domain.CatalogCategoryEntry;
import com.amazingenergy.saitamadomain.catalog.catalog.repository.CatalogEntryRepository;
import com.amazingenergy.saitamajpa.catalog.catalog.CatalogMapper;
import com.amazingenergy.saitamajpa.catalog.catalog.entity.CatalogCategoryEntryEntity;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class CatalogEntryRepositoryImpl extends BaseRepositoryImpl<UUID, CatalogCategoryEntry, CatalogCategoryEntryEntity> implements CatalogEntryRepository {

    private final JpaCatalogEntryRepository jpaRepository;
    private final CatalogMapper mapper;

    public CatalogEntryRepositoryImpl(JpaCatalogEntryRepository jpaRepository, CatalogMapper mapper) {
        super(jpaRepository);
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public void remove(CatalogCategoryEntry catalogEntry) {
        jpaRepository.delete(to(catalogEntry));
    }

    @Override
    public CatalogCategoryEntryEntity to(CatalogCategoryEntry source) {
        return mapper.to(source);
    }

    @Override
    public CatalogCategoryEntry from(CatalogCategoryEntryEntity destination) {
        return mapper.from(destination);
    }
}
