package com.amazingenergy.saitamajpa.merchant.repository;

import com.amazingenergy.saitamaappservice.merchant.model.MerchantStoreCriteria;
import com.amazingenergy.saitamajpa.merchant.entity.MerchantStoreEntity;
import org.springframework.data.domain.Page;

public interface CustomMerchantStoreRepository {
    Page<MerchantStoreEntity> listAll(MerchantStoreCriteria criteria);
}
