package com.amazingenergy.saitamajpa.merchant.query;

import com.amazingenergy.saitamaappservice.merchant.model.MerchantStoreCriteria;
import com.amazingenergy.saitamaappservice.merchant.query.MerchantStoreQueryService;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import com.amazingenergy.saitamajpa.merchant.MerchantMapper;
import com.amazingenergy.saitamajpa.merchant.entity.MerchantStoreEntity;
import com.amazingenergy.saitamajpa.merchant.repository.CustomMerchantStoreRepository;
import com.amazingenergy.saitamajpa.merchant.repository.JpaMerchantStoreRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MerchantStoreQueryServiceImpl implements MerchantStoreQueryService {
    private final JpaMerchantStoreRepository jpaRepository;
    private final CustomMerchantStoreRepository customMerchantStoreRepository;
    private final MerchantMapper mapper;

    public MerchantStoreQueryServiceImpl(JpaMerchantStoreRepository jpaRepository,
                                         CustomMerchantStoreRepository customMerchantStoreRepository,
                                         MerchantMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.customMerchantStoreRepository = customMerchantStoreRepository;
        this.mapper = mapper;
    }

    @Override
    public Page<MerchantStore> listAll(String storeName, Pageable pageable) {
        return jpaRepository.listAll(storeName, pageable).map(mapper::from);
    }

    @Override
    public Page<MerchantStore> listByGroup(String storeName, String storeCode, Pageable pageable) {
        var storeId = jpaRepository.findByCode(storeCode).map(MerchantStoreEntity::getId).orElse(null);
        return jpaRepository.listByGroup(storeCode, storeId, storeName, pageable).map(mapper::from);
    }

    @Override
    public Page<MerchantStore> listAllRetailers(String storeName, Pageable pageable) {
        return jpaRepository.listAllRetailers(storeName, pageable).map(mapper::from);
    }

    @Override
    public Page<MerchantStore> listChildren(String storeCode, String storeName, Pageable pageable) {
        return jpaRepository.listChildren(storeCode, storeName, pageable).map(mapper::from);
    }

    @Override
    public Page<MerchantStore> getByCriteria(MerchantStoreCriteria criteria) {
        return customMerchantStoreRepository.listAll(criteria).map(mapper::from);
    }
}
