package com.amazingenergy.saitamajpa.merchant;

import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import com.amazingenergy.saitamajpa.merchant.entity.MerchantStoreEntity;
import org.mapstruct.Mapper;

@Mapper
public interface MerchantMapper {
    MerchantStoreEntity to(MerchantStore source);

    MerchantStore from(MerchantStoreEntity destination);
}
