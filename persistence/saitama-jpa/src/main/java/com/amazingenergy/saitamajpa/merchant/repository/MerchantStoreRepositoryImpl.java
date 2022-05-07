package com.amazingenergy.saitamajpa.merchant.repository;

import com.amazingenergy.corejpa.repository.BaseRepositoryImpl;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import com.amazingenergy.saitamadomain.merchant.repository.MerchantStoreRepository;
import com.amazingenergy.saitamajpa.merchant.MerchantMapper;
import com.amazingenergy.saitamajpa.merchant.entity.MerchantStoreEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class MerchantStoreRepositoryImpl extends BaseRepositoryImpl<UUID, MerchantStore, MerchantStoreEntity> implements MerchantStoreRepository {

    private final JpaMerchantStoreRepository jpaRepository;
    private final MerchantMapper mapper;

    public MerchantStoreRepositoryImpl(JpaMerchantStoreRepository jpaRepository, MerchantMapper mapper) {
        super(jpaRepository);
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Optional<MerchantStore> getByCode(String code) {
        return jpaRepository.findByCode(code).map(this::from);
    }

    @Override
    public List<MerchantStore> findAllStoreNames(String code) {
        return jpaRepository.findAllStoreNames(code)
                .stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public boolean existByCode(String code) {
        return jpaRepository.existsByCode(code);
    }

    @Override
    public boolean isStoreInGroup(String code) {
        var storeId = jpaRepository.findByCode(code).map(MerchantStoreEntity::getId).orElse(null);
        var stores = jpaRepository.listByGroup(code, storeId);
        return stores.size() > 0;
    }

    @Override
    public MerchantStoreEntity to(MerchantStore source) {
        return mapper.to(source);
    }

    @Override
    public MerchantStore from(MerchantStoreEntity destination) {
        return mapper.from(destination);
    }
}
