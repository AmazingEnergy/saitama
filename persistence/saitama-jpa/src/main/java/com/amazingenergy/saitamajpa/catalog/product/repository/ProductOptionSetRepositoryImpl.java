package com.amazingenergy.saitamajpa.catalog.product.repository;

import com.amazingenergy.corejpa.repository.BaseRepositoryImpl;
import com.amazingenergy.saitamadomain.catalog.product.domain.attribute.ProductOptionSet;
import com.amazingenergy.saitamadomain.catalog.product.repository.ProductOptionSetRepository;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import com.amazingenergy.saitamadomain.reference.domain.Language;
import com.amazingenergy.saitamajpa.catalog.product.ProductMapper;
import com.amazingenergy.saitamajpa.catalog.product.entity.attribute.ProductOptionSetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class ProductOptionSetRepositoryImpl extends BaseRepositoryImpl<UUID, ProductOptionSet, ProductOptionSetEntity> implements ProductOptionSetRepository {
    private final JpaProductOptionSetRepository jpaRepository;
    private final ProductMapper mapper;

    public ProductOptionSetRepositoryImpl(JpaProductOptionSetRepository jpaRepository, ProductMapper mapper) {
        super(jpaRepository);
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public List<ProductOptionSet> listByStore(MerchantStore store, Language language) {
        return jpaRepository.findByStore(store.getId(), language.getId())
                .stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public Optional<ProductOptionSet> getById(MerchantStore store, UUID optionSetId, Language lang) {
        return jpaRepository.findOne(store.getId(), optionSetId, lang.getId()).map(this::from);
    }

    @Override
    public Optional<ProductOptionSet> getCode(MerchantStore store, String code) {
        return jpaRepository.findByCode(store.getId(), code).map(this::from);
    }

    @Override
    public List<ProductOptionSet> getByProductType(UUID productTypeId, MerchantStore store, Language lang) {
        return jpaRepository.findByProductType(productTypeId, store.getId(), lang.getId())
                .stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public ProductOptionSetEntity to(ProductOptionSet source) {
        return mapper.to(source);
    }

    @Override
    public ProductOptionSet from(ProductOptionSetEntity destination) {
        return mapper.from(destination);
    }
}
