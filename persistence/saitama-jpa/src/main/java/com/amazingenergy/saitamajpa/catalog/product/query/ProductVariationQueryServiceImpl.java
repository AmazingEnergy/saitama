package com.amazingenergy.saitamajpa.catalog.product.query;

import com.amazingenergy.saitamaappservice.catalog.product.query.ProductVariationQueryService;
import com.amazingenergy.saitamadomain.catalog.product.domain.availability.ProductVariation;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import com.amazingenergy.saitamadomain.reference.domain.Language;
import com.amazingenergy.saitamajpa.catalog.product.ProductMapper;
import com.amazingenergy.saitamajpa.catalog.product.repository.JpaProductVariationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductVariationQueryServiceImpl implements ProductVariationQueryService {

    private final JpaProductVariationRepository jpaRepository;
    private final ProductMapper mapper;

    public ProductVariationQueryServiceImpl(JpaProductVariationRepository jpaRepository, ProductMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Page<ProductVariation> getByMerchant(MerchantStore store, Language language, String code, Pageable pageable) {
        return jpaRepository.list(store.getId(), code, pageable).map(mapper::from);
    }
}
