package com.amazingenergy.saitamajpa.catalog.product.repository;

import com.amazingenergy.corejpa.repository.BaseRepositoryImpl;
import com.amazingenergy.saitamadomain.catalog.product.domain.attribute.ProductOption;
import com.amazingenergy.saitamadomain.catalog.product.repository.ProductAttributeRepository;
import com.amazingenergy.saitamadomain.catalog.product.repository.ProductOptionRepository;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import com.amazingenergy.saitamadomain.reference.domain.Language;
import com.amazingenergy.saitamajpa.catalog.product.ProductMapper;
import com.amazingenergy.saitamajpa.catalog.product.entity.attribute.ProductOptionEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class ProductOptionRepositoryImpl extends BaseRepositoryImpl<UUID, ProductOption, ProductOptionEntity> implements ProductOptionRepository {
    private final JpaProductOptionRepository jpaRepository;
    private final ProductAttributeRepository attributeRepository;
    private final ProductMapper mapper;

    public ProductOptionRepositoryImpl(JpaProductOptionRepository jpaRepository,
                                       ProductAttributeRepository attributeRepository,
                                       ProductMapper mapper) {
        super(jpaRepository);
        this.jpaRepository = jpaRepository;
        this.attributeRepository = attributeRepository;
        this.mapper = mapper;
    }

    @Override
    public List<ProductOption> listByStore(MerchantStore store, Language language) {
        return jpaRepository.findByStoreId(store.getId(), language.getId())
                .stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public List<ProductOption> listReadOnly(MerchantStore store, Language language) {
        return jpaRepository.findByReadOnly(store.getId(), language.getId(), true)
                .stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public List<ProductOption> getByName(MerchantStore store, String name, Language language) {
        return jpaRepository.findByName(store.getId(), name, language.getId())
                .stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public Optional<ProductOption> getByCode(MerchantStore store, String optionCode) {
        return jpaRepository.findByCode(store.getId(), optionCode).map(this::from);
    }

    @Override
    public Optional<ProductOption> getById(MerchantStore store, UUID optionId) {
        return jpaRepository.findOne(store.getId(), optionId).map(this::from);
    }

    @Override
    public void delete(ProductOption entity) {
        var attributes = attributeRepository.getByOptionId(entity.getMerchantStore(), entity.getId());
        for (var attribute : attributes) {
            attributeRepository.delete(attribute);
        }
        super.delete(entity);
    }

    @Override
    public ProductOptionEntity to(ProductOption source) {
        return mapper.to(source);
    }

    @Override
    public ProductOption from(ProductOptionEntity destination) {
        return mapper.from(destination);
    }
}
