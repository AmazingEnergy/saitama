package com.amazingenergy.saitamaappservice.merchant.query;

import com.amazingenergy.saitamaappservice.merchant.model.MerchantStoreCriteria;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MerchantStoreQueryService {
    Page<MerchantStore> listAll(String storeName, Pageable pageable);

    Page<MerchantStore> listByGroup(String storeName, String storeCode, Pageable pageable);

    Page<MerchantStore> listAllRetailers(String storeName, Pageable pageable);

    Page<MerchantStore> listChildren(String storeCode, String storeName, Pageable pageable);

    Page<MerchantStore> getByCriteria(MerchantStoreCriteria criteria);
}
