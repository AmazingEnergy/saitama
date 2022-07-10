package com.amazingenergy.saitamadomain.merchant.event;

import com.amazingenergy.core.domain.Event;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;

public class MerchantStoreCreated extends Event<MerchantStore> {
    public MerchantStoreCreated(MerchantStore aggregateRoot) {
        super(aggregateRoot);
    }
}
