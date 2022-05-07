package com.amazingenergy.saitamajpa.catalog.product.repository;

import com.amazingenergy.core.domain.AggregateRoot;
import com.amazingenergy.corejpa.repository.BaseRepositoryImpl;
import com.amazingenergy.saitamadomain.catalog.category.domain.Category;
import com.amazingenergy.saitamadomain.catalog.product.domain.Product;
import com.amazingenergy.saitamadomain.catalog.product.repository.ProductRepository;
import com.amazingenergy.saitamadomain.catalog.product.service.ProductUtils;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import com.amazingenergy.saitamadomain.reference.domain.Language;
import com.amazingenergy.saitamadomain.tax.domain.TaxClass;
import com.amazingenergy.saitamajpa.catalog.category.CategoryMapper;
import com.amazingenergy.saitamajpa.catalog.category.repository.JpaCategoryRepository;
import com.amazingenergy.saitamajpa.catalog.product.ProductMapper;
import com.amazingenergy.saitamajpa.catalog.product.entity.ProductEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class ProductRepositoryImpl extends BaseRepositoryImpl<UUID, Product, ProductEntity> implements ProductRepository {

    private final JpaProductRepository jpaRepository;
    private final JpaCategoryRepository jpaCategoryRepository;
    private final ProductMapper mapper;
    private final CategoryMapper categoryMapper;

    public ProductRepositoryImpl(JpaProductRepository jpaRepository,
                                 JpaCategoryRepository jpaCategoryRepository,
                                 ProductMapper mapper, CategoryMapper categoryMapper) {
        super(jpaRepository);
        this.jpaRepository = jpaRepository;
        this.jpaCategoryRepository = jpaCategoryRepository;
        this.mapper = mapper;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public Optional<Product> retrieveById(UUID id) {
        return jpaRepository.findById(id).map(this::from);
    }

    @Override
    public Optional<Product> getProductForLocale(UUID productId, Language language, Locale locale) {
        var regionList = List.of("*", locale.getCountry());

        var productOpt = jpaRepository.getProductForLocale(productId, language.getId(), new Date(), regionList);
        if (productOpt.isEmpty())
            return productOpt.map(this::from);
        var product = productOpt.map(this::from).get();
        ProductUtils.setToAvailability(product, locale);
        ProductUtils.setToLanguage(product, language);
        return Optional.of(product);
    }

    @Override
    public List<Product> getProductsForLocale(Category category, Language language, Locale locale) {
        var regionList = List.of("*", locale.getCountry());
        var store = category.getMerchantStore();

        var categories = jpaCategoryRepository.findByLineage(store.getId(), category.getLineage())
                .stream().map(categoryMapper::from).collect(Collectors.toList());

        var categoryIds = categories.stream().map(AggregateRoot::getId).collect(Collectors.toSet());
        categoryIds.add(category.getId());

        return jpaRepository.getProductsForLocale(store.getId(), categoryIds, language.getId(), new Date(), regionList, PageRequest.of(0, -1))
                .stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public List<Product> getProductsByIds(List<UUID> productIds) {
        return jpaRepository.getProductsListByIds(new HashSet<>(productIds), new Date())
                .stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public Optional<Product> getProductWithOnlyMerchantStoreById(UUID productId) {
        return jpaRepository.getProductWithOnlyMerchantStoreById(productId).map(this::from);
    }

    @Override
    public List<Product> listByStore(MerchantStore store) {
        return jpaRepository.listByStore(store.getId())
                .stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public List<Product> listByTaxClass(TaxClass taxClass) {
        return jpaRepository.listByTaxClass(taxClass.getId())
                .stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public List<Product> getProducts(List<UUID> categoryIds, Language language) {
        return jpaRepository.getProductsListByCategories(new HashSet<>(categoryIds), language.getId(), new Date())
                .stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public List<Product> getProducts(List<UUID> categoryIds) {
        return jpaRepository.getProductsListByCategories(new HashSet<>(categoryIds))
                .stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public Optional<Product> getBySeUrl(MerchantStore store, String seUrl, Locale locale) {
        var regionList = List.of("*", locale.getCountry());
        return jpaRepository.getByFriendlyUrl(store.getId(), new Date(), seUrl, regionList).map(this::from);
    }

    @Override
    public Optional<Product> getByCode(String productCode, Language language) {
        return jpaRepository.getByCodeAndLanguage(productCode, language.getId()).map(this::from);
    }

    @Override
    public Optional<Product> getByCode(String productCode, MerchantStore merchant) {
        return jpaRepository.getByCodeAndStore(productCode, merchant.getId()).map(this::from);
    }

    @Override
    public Optional<Product> findOne(UUID id, MerchantStore merchant) {
        return jpaRepository.getById(id, List.of(merchant.getId())).map(this::from);
    }

    @Override
    public ProductEntity to(Product source) {
        return mapper.to(source);
    }

    @Override
    public Product from(ProductEntity destination) {
        return mapper.from(destination);
    }
}
