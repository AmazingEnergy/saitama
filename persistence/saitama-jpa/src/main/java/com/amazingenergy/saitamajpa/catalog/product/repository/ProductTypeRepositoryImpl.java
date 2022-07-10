package com.amazingenergy.saitamajpa.catalog.product.repository;

import com.amazingenergy.corejpa.repository.BaseRepositoryImpl;
import com.amazingenergy.saitamadomain.catalog.product.domain.type.ProductType;
import com.amazingenergy.saitamadomain.catalog.product.repository.ProductTypeRepository;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import com.amazingenergy.saitamadomain.reference.domain.Language;
import com.amazingenergy.saitamajpa.catalog.product.ProductMapper;
import com.amazingenergy.saitamajpa.catalog.product.entity.type.ProductTypeEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class ProductTypeRepositoryImpl extends BaseRepositoryImpl<UUID, ProductType, ProductTypeEntity> implements ProductTypeRepository {

    private final JpaProductTypeRepository jpaRepository;
    private final ProductMapper mapper;

    public ProductTypeRepositoryImpl(JpaProductTypeRepository jpaRepository, ProductMapper mapper) {
        super(jpaRepository);
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Optional<ProductType> getByCode(String code, MerchantStore store, Language language) {
        return jpaRepository.findByCode(code, store.getId()).map(this::from);
    }

    @Override
    public Optional<ProductType> getById(UUID id, MerchantStore store, Language language) {
        return jpaRepository.findById(id, store.getId(), language.getId()).map(this::from);
    }

    @Override
    public Optional<ProductType> getById(UUID id, MerchantStore store) {
        return jpaRepository.findById(id, store.getId()).map(this::from);
    }

    @Override
    public Optional<ProductType> getProductType(String productTypeCode) {
        return jpaRepository.findByCode(productTypeCode).map(this::from);
    }

    @Override
    public List<ProductType> listProductTypes(List<UUID> ids, MerchantStore store, Language language) {
        return jpaRepository.findByIds(ids, store.getId(), language.getId())
                .stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public ProductTypeEntity to(ProductType source) {
        return mapper.to(source);
    }

    @Override
    public ProductType from(ProductTypeEntity destination) {
        return mapper.from(destination);
    }
}
