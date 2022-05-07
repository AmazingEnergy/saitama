package com.amazingenergy.saitamajpa.catalog.catalog.query;

import com.amazingenergy.saitamaappservice.catalog.catalog.query.CatalogEntryQueryService;
import com.amazingenergy.saitamadomain.catalog.catalog.domain.Catalog;
import com.amazingenergy.saitamadomain.catalog.catalog.domain.CatalogCategoryEntry;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import com.amazingenergy.saitamadomain.reference.domain.Language;
import com.amazingenergy.saitamajpa.catalog.catalog.CatalogMapper;
import com.amazingenergy.saitamajpa.catalog.catalog.repository.JpaCatalogEntryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CatalogEntryQueryServiceImpl implements CatalogEntryQueryService {
    private final JpaCatalogEntryRepository jpaRepository;
    private final CatalogMapper mapper;

    public CatalogEntryQueryServiceImpl(JpaCatalogEntryRepository jpaRepository, CatalogMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Page<CatalogCategoryEntry> find(Catalog catalog, MerchantStore store, Language language, Pageable pageable) {
        return jpaRepository.listByCatalog(catalog.getId(), store.getId(), language.getId(), pageable).map(mapper::from);
    }
}
