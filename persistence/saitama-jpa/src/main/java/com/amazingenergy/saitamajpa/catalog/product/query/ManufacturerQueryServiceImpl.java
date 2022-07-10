package com.amazingenergy.saitamajpa.catalog.product.query;

import com.amazingenergy.saitamaappservice.catalog.product.query.ManufacturerQueryService;
import com.amazingenergy.saitamadomain.catalog.product.domain.manufacturer.Manufacturer;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import com.amazingenergy.saitamadomain.reference.domain.Language;
import com.amazingenergy.saitamajpa.catalog.product.ProductMapper;
import com.amazingenergy.saitamajpa.catalog.product.repository.JpaManufacturerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ManufacturerQueryServiceImpl implements ManufacturerQueryService {
    private final JpaManufacturerRepository jpaRepository;
    private final ProductMapper mapper;

    public ManufacturerQueryServiceImpl(JpaManufacturerRepository jpaRepository, ProductMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Page<Manufacturer> listByStore(MerchantStore store, String name, Pageable pageable) {
        return jpaRepository.findByStore(store.getId(), name, pageable).map(mapper::from);
    }

    @Override
    public Page<Manufacturer> listByStore(MerchantStore store, Language language, Pageable pageable) {
        return jpaRepository.findByStore(store.getId(), language.getId(), null, pageable).map(mapper::from);
    }

    @Override
    public Page<Manufacturer> listByStore(MerchantStore store, Language language, String name, Pageable pageable) {
        return jpaRepository.findByStore(store.getId(), language.getId(), name, pageable).map(mapper::from);
    }
}
