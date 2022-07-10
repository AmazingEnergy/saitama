package com.amazingenergy.saitamajpa.catalog.product.repository;

import com.amazingenergy.corejpa.repository.BaseRepositoryImpl;
import com.amazingenergy.saitamadomain.catalog.product.domain.attribute.ProductOptionValue;
import com.amazingenergy.saitamadomain.catalog.product.repository.ProductAttributeRepository;
import com.amazingenergy.saitamadomain.catalog.product.repository.ProductOptionValueRepository;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import com.amazingenergy.saitamadomain.reference.domain.Language;
import com.amazingenergy.saitamajpa.catalog.product.ProductMapper;
import com.amazingenergy.saitamajpa.catalog.product.entity.attribute.ProductOptionValueEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class ProductOptionValueRepositoryImpl extends BaseRepositoryImpl<UUID, ProductOptionValue, ProductOptionValueEntity> implements ProductOptionValueRepository {

    private final JpaProductOptionValueRepository jpaRepository;
    private final ProductAttributeRepository attributeRepository;
    private final ProductMapper mapper;

    public ProductOptionValueRepositoryImpl(JpaProductOptionValueRepository jpaRepository,
                                            ProductAttributeRepository attributeRepository,
                                            ProductMapper mapper) {
        super(jpaRepository);
        this.jpaRepository = jpaRepository;
        this.attributeRepository = attributeRepository;
        this.mapper = mapper;
    }

    @Override
    public List<ProductOptionValue> getByName(MerchantStore store, String name, Language language) {
        return jpaRepository.findByName(store.getId(), name, language.getId())
                .stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public List<ProductOptionValue> listByStore(MerchantStore store, Language language) {
        return jpaRepository.findByStoreId(store.getId(), language.getId())
                .stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public List<ProductOptionValue> listByStoreNoReadOnly(MerchantStore store, Language language) {
        return jpaRepository.findByReadOnly(store.getId(), language.getId(), false)
                .stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public Optional<ProductOptionValue> getByCode(MerchantStore store, String optionValueCode) {
        return jpaRepository.findByCode(store.getId(), optionValueCode).map(this::from);
    }

    @Override
    public Optional<ProductOptionValue> getById(MerchantStore store, UUID optionValueId) {
        return jpaRepository.findOne(store.getId(), optionValueId).map(this::from);
    }

    @Override
    public void delete(ProductOptionValue entity) {
        var attributes = attributeRepository.getByOptionValueId(entity.getMerchantStore(), entity.getId());
        for (var attribute : attributes) {
            attributeRepository.delete(attribute);
        }
        super.delete(entity);
    }

    @Override
    public ProductOptionValueEntity to(ProductOptionValue source) {
        return mapper.to(source);
    }

    @Override
    public ProductOptionValue from(ProductOptionValueEntity destination) {
        return mapper.from(destination);
    }
}
