package com.amazingenergy.saitamaappservice.catalog.product.query;

import com.amazingenergy.saitamadomain.catalog.product.domain.manufacturer.Manufacturer;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import com.amazingenergy.saitamadomain.reference.domain.Language;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ManufacturerQueryService {
    Page<Manufacturer> listByStore(MerchantStore store, String name, Pageable pageable);

    Page<Manufacturer> listByStore(MerchantStore store, Language language, Pageable pageable);

    Page<Manufacturer> listByStore(MerchantStore store, Language language, String name, Pageable pageable);
}
