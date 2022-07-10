package com.amazingenergy.saitamajpa.customer.repository;

import com.amazingenergy.saitamaappservice.customer.model.CustomerCriteria;
import com.amazingenergy.saitamajpa.customer.entity.CustomerEntity;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface CustomCustomerRepository {
    Page<CustomerEntity> listByStore(UUID storeId, CustomerCriteria criteria);
}
