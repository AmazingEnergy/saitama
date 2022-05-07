package com.amazingenergy.saitamaappservice.catalog.product.query;

import com.amazingenergy.saitamadomain.catalog.product.domain.availability.ProductVariation;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import com.amazingenergy.saitamadomain.reference.domain.Language;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductVariationQueryService {
    Page<ProductVariation> getByMerchant(MerchantStore store, Language language, String code, Pageable pageable);
}
