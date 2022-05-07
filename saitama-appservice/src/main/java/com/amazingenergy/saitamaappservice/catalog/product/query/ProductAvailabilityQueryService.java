package com.amazingenergy.saitamaappservice.catalog.product.query;

import com.amazingenergy.saitamadomain.catalog.product.domain.Product;
import com.amazingenergy.saitamadomain.catalog.product.domain.availability.ProductAvailability;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductAvailabilityQueryService {
    Page<ProductAvailability> listByProduct(Product product, MerchantStore store, String child, Pageable pageable);
}
