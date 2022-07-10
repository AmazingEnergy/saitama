package com.amazingenergy.saitamajpa.catalog.product.repository;

import com.amazingenergy.corejpa.repository.BaseRepositoryImpl;
import com.amazingenergy.saitamadomain.catalog.category.domain.Category;
import com.amazingenergy.saitamadomain.catalog.product.domain.manufacturer.Manufacturer;
import com.amazingenergy.saitamadomain.catalog.product.repository.ManufacturerRepository;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import com.amazingenergy.saitamadomain.reference.domain.Language;
import com.amazingenergy.saitamajpa.catalog.product.ProductMapper;
import com.amazingenergy.saitamajpa.catalog.product.entity.manufacturer.ManufacturerEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class ManufacturerRepositoryImpl extends BaseRepositoryImpl<UUID, Manufacturer, ManufacturerEntity> implements ManufacturerRepository {

    private final JpaManufacturerRepository jpaRepository;
    private final ProductMapper mapper;

    public ManufacturerRepositoryImpl(JpaManufacturerRepository jpaRepository, ProductMapper mapper) {
        super(jpaRepository);
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Manufacturer> listByStore(MerchantStore store, Language language) {
        return jpaRepository.findByStoreAndLanguage(store.getId(), language.getId())
                .stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public List<Manufacturer> listByStore(MerchantStore store) {
        return jpaRepository.findByStore(store.getId())
                .stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public Long getCountManufacturerAttachedProducts(Manufacturer manufacturer) {
        return jpaRepository.countByProduct(manufacturer.getId());
    }

    @Override
    public Optional<Manufacturer> getByCode(MerchantStore store, String code) {
        return jpaRepository.findByCodeAndMerchantStore(code, store.getId()).map(this::from);
    }

    @Override
    public List<Manufacturer> listByProductsByCategoriesId(MerchantStore store, List<UUID> categoryIds, Language language) {
        return jpaRepository.findByCategoriesAndLanguage(categoryIds, language.getId())
                .stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public List<Manufacturer> listByProductsInCategory(MerchantStore store, Category category, Language language) {
        return jpaRepository.findByProductInCategoryId(store.getId(), category.getLineage(), language.getId())
                .stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public int count(MerchantStore store) {
        return jpaRepository.count(store.getId());
    }

    @Override
    public ManufacturerEntity to(Manufacturer source) {
        return mapper.to(source);
    }

    @Override
    public Manufacturer from(ManufacturerEntity destination) {
        return mapper.from(destination);
    }
}
