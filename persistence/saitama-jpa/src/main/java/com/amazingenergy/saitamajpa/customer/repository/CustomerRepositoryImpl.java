package com.amazingenergy.saitamajpa.customer.repository;

import com.amazingenergy.corejpa.repository.BaseRepositoryImpl;
import com.amazingenergy.saitamadomain.customer.domain.Customer;
import com.amazingenergy.saitamadomain.customer.repository.CustomerRepository;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import com.amazingenergy.saitamajpa.customer.CustomerMapper;
import com.amazingenergy.saitamajpa.customer.entity.CustomerEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class CustomerRepositoryImpl extends BaseRepositoryImpl<UUID, Customer, CustomerEntity> implements CustomerRepository {

    private final JpaCustomerRepository jpaRepository;
    private final CustomerMapper mapper;

    public CustomerRepositoryImpl(JpaCustomerRepository jpaRepository, CustomerMapper mapper) {
        super(jpaRepository);
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Customer> getByName(String firstName) {
        return jpaRepository.findByName(firstName)
                .stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public List<Customer> getListByStore(MerchantStore store) {
        return jpaRepository.findByStore(store.getId())
                .stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public Optional<Customer> getByNick(String nick) {
        return jpaRepository.findByNick(nick).map(this::from);
    }

    @Override
    public Optional<Customer> getByNick(String nick, UUID storeId) {
        return jpaRepository.findByNick(nick, storeId).map(this::from);
    }

    @Override
    public Optional<Customer> getByNick(String nick, String code) {
        return jpaRepository.findByNick(nick, code).map(this::from);
    }

    @Override
    public CustomerEntity to(Customer source) {
        return mapper.to(source);
    }

    @Override
    public Customer from(CustomerEntity destination) {
        return mapper.from(destination);
    }
}
