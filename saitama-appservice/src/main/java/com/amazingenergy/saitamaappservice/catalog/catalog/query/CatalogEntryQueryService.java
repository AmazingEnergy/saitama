package com.amazingenergy.saitamaappservice.catalog.catalog.query;

import com.amazingenergy.saitamadomain.catalog.catalog.domain.Catalog;
import com.amazingenergy.saitamadomain.catalog.catalog.domain.CatalogCategoryEntry;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import com.amazingenergy.saitamadomain.reference.domain.Language;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CatalogEntryQueryService {
    Page<CatalogCategoryEntry> find(Catalog catalog, MerchantStore store, Language language, Pageable pageable);
}
