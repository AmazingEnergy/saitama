package com.amazingenergy.saitamajpa.catalog.catalog.query;

import com.amazingenergy.saitamaappservice.catalog.catalog.query.CatalogQueryService;
import com.amazingenergy.saitamadomain.catalog.catalog.domain.Catalog;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import com.amazingenergy.saitamadomain.reference.domain.Language;
import com.amazingenergy.saitamajpa.catalog.catalog.CatalogMapper;
import com.amazingenergy.saitamajpa.catalog.catalog.repository.JpaCatalogRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CatalogQueryServiceImpl implements CatalogQueryService {

    private final JpaCatalogRepository jpaRepository;
    private final CatalogMapper mapper;

    public CatalogQueryServiceImpl(JpaCatalogRepository jpaRepository, CatalogMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Page<Catalog> find(MerchantStore store, Language language, String name, Pageable pageable) {
        return jpaRepository.listByStore(store.getId(), name, pageable).map(mapper::from);
    }
}
