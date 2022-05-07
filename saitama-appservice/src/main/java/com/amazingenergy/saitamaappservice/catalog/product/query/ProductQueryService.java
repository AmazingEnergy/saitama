package com.amazingenergy.saitamaappservice.catalog.product.query;

import com.amazingenergy.saitamaappservice.catalog.product.model.ProductCriteria;
import com.amazingenergy.saitamadomain.catalog.product.domain.Product;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import com.amazingenergy.saitamadomain.reference.domain.Language;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductQueryService {
    /**
     * List using Page interface in order to unify all page requests (since 2.16.0)
     */
    Page<Product> listByStore(MerchantStore store, Language language, ProductCriteria criteria, Pageable pageable);

    List<Product> listByStore(MerchantStore store, Language language, ProductCriteria criteria);
}
