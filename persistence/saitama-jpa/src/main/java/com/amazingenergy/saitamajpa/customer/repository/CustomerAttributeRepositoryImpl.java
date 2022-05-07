package com.amazingenergy.saitamajpa.customer.repository;

import com.amazingenergy.corejpa.repository.BaseRepositoryImpl;
import com.amazingenergy.saitamadomain.customer.domain.Customer;
import com.amazingenergy.saitamadomain.customer.domain.attribute.CustomerAttribute;
import com.amazingenergy.saitamadomain.customer.repository.CustomerAttributeRepository;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import com.amazingenergy.saitamajpa.customer.CustomerMapper;
import com.amazingenergy.saitamajpa.customer.entity.attribute.CustomerAttributeEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class CustomerAttributeRepositoryImpl extends BaseRepositoryImpl<UUID, CustomerAttribute, CustomerAttributeEntity> implements CustomerAttributeRepository {

    private final JpaCustomerAttributeRepository jpaRepository;
    private final CustomerMapper mapper;

    public CustomerAttributeRepositoryImpl(JpaCustomerAttributeRepository jpaRepository, CustomerMapper mapper) {
        super(jpaRepository);
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Optional<CustomerAttribute> getByCustomerOptionId(MerchantStore store, UUID customerId, UUID id) {
        return jpaRepository.findByOptionId(store.getId(), customerId, id).map(this::from);
    }

    @Override
    public List<CustomerAttribute> getByCustomerOptionValueId(MerchantStore store, UUID id) {
        return jpaRepository.findByOptionValueId(store.getId(), id)
                .stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public List<CustomerAttribute> getByOptionId(MerchantStore store, UUID id) {
        return jpaRepository.findByOptionId(store.getId(), id)
                .stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public List<CustomerAttribute> getByCustomer(MerchantStore store, Customer customer) {
        return jpaRepository.findByCustomerId(store.getId(), customer.getId())
                .stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public CustomerAttributeEntity to(CustomerAttribute source) {
        return mapper.to(source);
    }

    @Override
    public CustomerAttribute from(CustomerAttributeEntity destination) {
        return mapper.from(destination);
    }
}
