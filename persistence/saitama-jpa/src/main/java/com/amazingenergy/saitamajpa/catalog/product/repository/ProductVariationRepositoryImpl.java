package com.amazingenergy.saitamajpa.catalog.product.repository;

import com.amazingenergy.corejpa.repository.BaseRepositoryImpl;
import com.amazingenergy.saitamadomain.catalog.product.domain.availability.ProductVariation;
import com.amazingenergy.saitamadomain.catalog.product.repository.ProductVariationRepository;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import com.amazingenergy.saitamadomain.reference.domain.Language;
import com.amazingenergy.saitamajpa.catalog.product.ProductMapper;
import com.amazingenergy.saitamajpa.catalog.product.entity.availability.ProductVariationEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class ProductVariationRepositoryImpl extends BaseRepositoryImpl<UUID, ProductVariation, ProductVariationEntity> implements ProductVariationRepository {

    private final JpaProductVariationRepository jpaRepository;
    private final ProductMapper mapper;

    public ProductVariationRepositoryImpl(JpaProductVariationRepository jpaRepository, ProductMapper mapper) {
        super(jpaRepository);
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Optional<ProductVariation> getById(MerchantStore store, UUID id, Language lang) {
        return jpaRepository.findOne(store.getId(), id, lang.getId()).map(this::from);
    }

    @Override
    public Optional<ProductVariation> getByCode(MerchantStore store, String code) {
        return jpaRepository.findByCode(code, store.getId()).map(this::from);
    }

    @Override
    public ProductVariationEntity to(ProductVariation source) {
        return mapper.to(source);
    }

    @Override
    public ProductVariation from(ProductVariationEntity destination) {
        return mapper.from(destination);
    }
}
