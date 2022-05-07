package com.amazingenergy.saitamaappservice.catalog.product.query;

import com.amazingenergy.saitamadomain.catalog.product.domain.type.ProductType;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import com.amazingenergy.saitamadomain.reference.domain.Language;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductTypeQueryService {
    Page<ProductType> getByMerchant(MerchantStore store, Language language, Pageable pageable);
}
