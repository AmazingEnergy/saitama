package com.amazingenergy.saitamajpa.catalog.category.query;

import com.amazingenergy.saitamaappservice.catalog.category.query.CategoryQueryService;
import com.amazingenergy.saitamadomain.catalog.category.domain.Category;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import com.amazingenergy.saitamadomain.reference.domain.Language;
import com.amazingenergy.saitamajpa.catalog.category.CategoryMapper;
import com.amazingenergy.saitamajpa.catalog.category.repository.JpaCategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryQueryServiceImpl implements CategoryQueryService {
    private final JpaCategoryRepository jpaRepository;
    private final CategoryMapper mapper;

    public CategoryQueryServiceImpl(JpaCategoryRepository jpaRepository, CategoryMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Page<Category> find(MerchantStore store, Language language, String name, Pageable pageable) {
        return jpaRepository.listByStore(store.getId(), language.getId(), name, pageable).map(mapper::from);
    }
}
