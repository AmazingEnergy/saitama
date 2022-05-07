package com.amazingenergy.saitamajpa.catalog.product.query;

import com.amazingenergy.saitamaappservice.catalog.product.model.ProductCriteria;
import com.amazingenergy.saitamaappservice.catalog.product.query.ProductQueryService;
import com.amazingenergy.saitamadomain.catalog.product.domain.Product;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import com.amazingenergy.saitamadomain.reference.domain.Language;
import com.amazingenergy.saitamajpa.catalog.product.ProductMapper;
import com.amazingenergy.saitamajpa.catalog.product.repository.JpaProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductQueryServiceImpl implements ProductQueryService {
    private final JpaProductRepository jpaRepository;
    private final ProductMapper mapper;

    public ProductQueryServiceImpl(JpaProductRepository jpaRepository, ProductMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Page<Product> listByStore(MerchantStore store, Language language, ProductCriteria criteria, Pageable pageable) {
        return jpaRepository.listByStore(store.getId(), language.getCode(), criteria).map(mapper::from);
    }

    @Override
    public List<Product> listByStore(MerchantStore store, Language language, ProductCriteria criteria) {
        return jpaRepository.listByStore(store.getId(), language.getCode(), criteria)
                .stream().map(mapper::from).collect(Collectors.toList());
    }
}
