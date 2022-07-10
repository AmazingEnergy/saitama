package com.amazingenergy.saitamaappservice.catalog.category.query;

import com.amazingenergy.saitamadomain.catalog.category.domain.Category;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import com.amazingenergy.saitamadomain.reference.domain.Language;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryQueryService {
    Page<Category> find(MerchantStore store, Language language, String name, Pageable pageable);
}
