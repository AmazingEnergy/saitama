package com.amazingenergy.saitamajpa.catalog.category.repository;

import com.amazingenergy.core.domain.AggregateRoot;
import com.amazingenergy.corejpa.repository.BaseRepositoryImpl;
import com.amazingenergy.saitamadomain.catalog.category.domain.Category;
import com.amazingenergy.saitamadomain.catalog.category.repository.CategoryRepository;
import com.amazingenergy.saitamadomain.catalog.product.repository.ProductRepository;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import com.amazingenergy.saitamadomain.reference.domain.Language;
import com.amazingenergy.saitamadomain.seedwork.constant.Constants;
import com.amazingenergy.saitamajpa.catalog.category.CategoryMapper;
import com.amazingenergy.saitamajpa.catalog.category.entity.CategoryEntity;
import com.amazingenergy.saitamajpa.merchant.MerchantMapper;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

import static com.amazingenergy.saitamadomain.seedwork.constant.UUID.emptyUUID;

@Repository
public class CategoryRepositoryImpl extends BaseRepositoryImpl<UUID, Category, CategoryEntity> implements CategoryRepository {

    private final ProductRepository productRepository;
    private final JpaCategoryRepository jpaCategoryRepository;
    private final JpaCategoryDescriptionRepository jpaDescriptionRepository;
    private final CategoryMapper categoryMapper;
    private final MerchantMapper merchantMapper;

    public CategoryRepositoryImpl(ProductRepository productRepository,
                                  JpaCategoryRepository jpaCategoryRepository,
                                  JpaCategoryDescriptionRepository jpaDescriptionRepository,
                                  CategoryMapper categoryMapper, MerchantMapper merchantMapper) {
        super(jpaCategoryRepository);
        this.productRepository = productRepository;
        this.jpaCategoryRepository = jpaCategoryRepository;
        this.jpaDescriptionRepository = jpaDescriptionRepository;
        this.categoryMapper = categoryMapper;
        this.merchantMapper = merchantMapper;
    }

    @Override
    public void create(Category category) {
        super.create(category);
        StringBuilder lineage = new StringBuilder();
        Category parent = category.getParent();
        if (parent != null && !Objects.equals(parent.getId(), emptyUUID())) {
            Category parentCategory = this.findById(parent.getId()).get();
            lineage.append(parentCategory.getLineage()).append(category.getId()).append(Constants.SLASH);
            category.setDepth(parentCategory.getDepth() + 1);
        } else {
            lineage.append(Constants.SLASH).append(category.getId()).append(Constants.SLASH);
            category.setDepth(0);
        }
        category.setLineage(lineage.toString());
        super.update(category);
    }

    @Override
    public List<Category> getListByLineage(MerchantStore store, String lineage) {
        return jpaCategoryRepository.findByLineage(store.getId(), lineage)
                .stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public Category getOneByLanguage(UUID categoryId, Language language) {
        var category = jpaCategoryRepository.findByIdAndLanguage(categoryId, language.getId());
        return category == null ? null : this.from(category);
    }

    @Override
    public List<Object[]> countProductsByCategories(MerchantStore store, List<UUID> categoryIds) {
        return jpaCategoryRepository.countProductsByCategories(
                merchantMapper.to(store), categoryIds);
    }

    @Override
    public List<Category> listByCodes(MerchantStore store, List<String> codes, Language language) {
        return jpaCategoryRepository.findByCodes(store.getId(), codes, language.getId())
                .stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public List<Category> listByIds(MerchantStore store, List<UUID> ids, Language language) {
        return jpaCategoryRepository.findByIds(store.getId(), ids, language.getId())
                .stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public void saveOrUpdate(Category category) {
        if (!Objects.equals(category.getId(), emptyUUID())) {
            super.update(category);
        } else {
            this.create(category);
        }
    }

    @Override
    public List<Category> getListByLineage(String storeCode, String lineage) {
        return jpaCategoryRepository.findByLineage(storeCode, lineage)
                .stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public List<Category> listBySeUrl(MerchantStore store, String seUrl) {
        return jpaCategoryRepository.listByFriendlyUrl(store.getId(), seUrl)
                .stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public Category getBySeUrl(MerchantStore store, String seUrl) {
        var category = jpaCategoryRepository.findByFriendlyUrl(store.getId(), seUrl);
        return category == null ? null : this.from(category);
    }

    @Override
    public Category getByCode(MerchantStore store, String code) {
        var category = jpaCategoryRepository.findByCode(store.getId(), code);
        return category == null ? null : this.from(category);
    }

    @Override
    public Category getByCode(String storeCode, String code) {
        var category = jpaCategoryRepository.findByCode(storeCode, code);
        return category == null ? null : this.from(category);
    }

    @Override
    public Category getById(UUID id, UUID merchantId) {
        var categoryEntity = jpaCategoryRepository.findByIdAndStore(id, merchantId);
        if (categoryEntity == null)
            return null;
        var descriptions = jpaDescriptionRepository.listByCategoryId(id);
        var category = this.from(categoryEntity);
        category.setDescriptions(descriptions.stream().map(
                categoryMapper::from).collect(Collectors.toSet()));
        return category;
    }

    @Override
    public List<Category> listByParent(Category category) {
        return jpaCategoryRepository.listByStoreAndParent(null, to(category))
                .stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public List<Category> listByStoreAndParent(MerchantStore store, Category category) {
        return jpaCategoryRepository.listByStoreAndParent(merchantMapper.to(store), to(category))
                .stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public List<Category> listByParent(Category category, Language language) {
        return jpaCategoryRepository.findByParent(category.getId(), language.getId())
                .stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public void delete(Category category) {
        // get sub-categories with lineage
        String lineage = category.getLineage() + category.getId() + Constants.SLASH;
        List<Category> lineageCategories = this.getListByLineage(category.getMerchantStore(), lineage);
        lineageCategories.add(category);
        Collections.reverse(lineageCategories);

        var categoryIds = lineageCategories.stream().map(AggregateRoot::getId).collect(Collectors.toList());

        var products = productRepository.getProducts(categoryIds);
        // org.hibernate.Session session = em.unwrap(org.hibernate.Session.class);
        // need to refresh the session to update all product categories

        for (var product : products) {
            // session.evict(product);
            // refresh product, so we get all product categories
            var productCategories = product.getCategories();
            if (productCategories.size() > 1) {
                for (Category c : lineageCategories) {
                    productCategories.remove(c);
                    productRepository.update(product);
                }

                if (product.getCategories() == null || product.getCategories().size() == 0) {
                    productRepository.delete(product);
                }
            } else {
                productRepository.delete(product);
            }
        }

        // remove this category and all sub-categories
        super.delete(category);
    }

    @Override
    public List<Category> getListByDepth(MerchantStore store, int depth) {
        return jpaCategoryRepository.findByDepth(store.getId(), depth)
                .stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public List<Category> getListByDepthFilterByFeatured(MerchantStore store, int depth, Language language) {
        return jpaCategoryRepository.findByDepthFilterByFeatured(store.getId(), depth, language.getId())
                .stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public List<Category> getByName(MerchantStore store, String name, Language language) {
        return jpaCategoryRepository.findByName(store.getId(), name, language.getId())
                .stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public List<Category> listByStore(MerchantStore store) {
        return jpaCategoryRepository.findByStore(store.getId())
                .stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public List<Category> listByStore(MerchantStore store, Language language) {
        return jpaCategoryRepository.findByStore(store.getId(), language.getId())
                .stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public Category getById(UUID categoryId, UUID merchantId, UUID languageId) {
        var category = jpaCategoryRepository.findById(merchantId, categoryId, languageId);
        return category == null ? null : this.from(category);
    }

    @Override
    public List<Category> getListByDepth(MerchantStore store, int depth, Language language) {
        return jpaCategoryRepository.find(store.getId(), depth, language.getId(), null)
                .stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public Category getById(MerchantStore store, UUID id) {
        var category = jpaCategoryRepository.findById(id, store.getCode());
        return category == null ? null : this.from(category);
    }

    @Override
    public int count(MerchantStore store) {
        return jpaCategoryRepository.count(store.getId());
    }

    @Override
    public CategoryEntity to(Category source) {
        return categoryMapper.to(source);
    }

    @Override
    public Category from(CategoryEntity destination) {
        return categoryMapper.from(destination);
    }
}
