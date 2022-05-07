package com.amazingenergy.saitamaappservice.customer.query;

import com.amazingenergy.saitamaappservice.customer.model.CustomerCriteria;
import com.amazingenergy.saitamadomain.customer.domain.Customer;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import org.springframework.data.domain.Page;

public interface CustomerQueryService {
    Page<Customer> getListByStore(MerchantStore store, CustomerCriteria criteria);
}
