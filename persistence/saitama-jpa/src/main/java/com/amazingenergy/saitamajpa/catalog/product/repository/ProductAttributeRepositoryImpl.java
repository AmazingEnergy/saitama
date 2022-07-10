package com.amazingenergy.saitamajpa.catalog.product.repository;

import com.amazingenergy.corejpa.repository.BaseRepositoryImpl;
import com.amazingenergy.saitamadomain.catalog.product.domain.Product;
import com.amazingenergy.saitamadomain.catalog.product.domain.attribute.ProductAttribute;
import com.amazingenergy.saitamadomain.catalog.product.repository.ProductAttributeRepository;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import com.amazingenergy.saitamadomain.reference.domain.Language;
import com.amazingenergy.saitamajpa.catalog.product.ProductMapper;
import com.amazingenergy.saitamajpa.catalog.product.entity.attribute.ProductAttributeEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class ProductAttributeRepositoryImpl extends BaseRepositoryImpl<UUID, ProductAttribute, ProductAttributeEntity> implements ProductAttributeRepository {

    private final JpaProductAttributeRepository jpaRepository;
    private final ProductMapper mapper;

    public ProductAttributeRepositoryImpl(JpaProductAttributeRepository jpaRepository, ProductMapper mapper) {
        super(jpaRepository);
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public void saveOrUpdate(ProductAttribute productAttribute) {
        jpaRepository.save(this.to(productAttribute));
    }

    @Override
    public List<ProductAttribute> getByOptionId(MerchantStore store, UUID id) {
        return jpaRepository.findByOptionId(store.getId(), id)
                .stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public List<ProductAttribute> getByOptionValueId(MerchantStore store, UUID id) {
        return jpaRepository.findByOptionValueId(store.getId(), id)
                .stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public List<ProductAttribute> getByProductId(MerchantStore store, Product product, Language language) {
        return jpaRepository.findByProductId(store.getId(), product.getId(), language.getId())
                .stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public List<ProductAttribute> getByProductId(MerchantStore store, Product product) {
        return jpaRepository.findByProductId(store.getId(), product.getId())
                .stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public List<ProductAttribute> getByAttributeIds(MerchantStore store, Product product, List<UUID> ids) {
        return jpaRepository.findByAttributeIds(store.getId(), product.getId(), ids)
                .stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public List<ProductAttribute> getProductAttributesByCategoryLineage(MerchantStore store, String lineage, Language language) {
        return jpaRepository.findOptionsByCategoryLineage(store.getId(), lineage, language.getId())
                .stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public ProductAttributeEntity to(ProductAttribute source) {
        return mapper.to(source);
    }

    @Override
    public ProductAttribute from(ProductAttributeEntity destination) {
        return mapper.from(destination);
    }
}
