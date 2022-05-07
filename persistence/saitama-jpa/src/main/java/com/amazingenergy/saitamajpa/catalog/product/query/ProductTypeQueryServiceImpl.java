package com.amazingenergy.saitamajpa.catalog.product.query;

import com.amazingenergy.saitamaappservice.catalog.product.query.ProductTypeQueryService;
import com.amazingenergy.saitamadomain.catalog.product.domain.type.ProductType;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import com.amazingenergy.saitamadomain.reference.domain.Language;
import com.amazingenergy.saitamajpa.catalog.product.ProductMapper;
import com.amazingenergy.saitamajpa.catalog.product.repository.JpaProductTypeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductTypeQueryServiceImpl implements ProductTypeQueryService {
    private final JpaProductTypeRepository jpaRepository;
    private final ProductMapper mapper;

    public ProductTypeQueryServiceImpl(JpaProductTypeRepository jpaRepository, ProductMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Page<ProductType> getByMerchant(MerchantStore store, Language language, Pageable pageable) {
        return jpaRepository.listByStore(store.getId(), pageable).map(mapper::from);
    }
}
