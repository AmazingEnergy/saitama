package com.amazingenergy.saitamajpa.customer.query;

import com.amazingenergy.saitamaappservice.customer.model.CustomerCriteria;
import com.amazingenergy.saitamaappservice.customer.query.CustomerQueryService;
import com.amazingenergy.saitamadomain.customer.domain.Customer;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import com.amazingenergy.saitamajpa.customer.CustomerMapper;
import com.amazingenergy.saitamajpa.customer.repository.JpaCustomerRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class CustomerQueryServiceImpl implements CustomerQueryService {

    private final JpaCustomerRepository jpaRepository;
    private final CustomerMapper mapper;

    public CustomerQueryServiceImpl(JpaCustomerRepository jpaRepository, CustomerMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Page<Customer> getListByStore(MerchantStore store, CustomerCriteria criteria) {
        return jpaRepository.listByStore(store.getId(), criteria).map(mapper::from);
    }
}
