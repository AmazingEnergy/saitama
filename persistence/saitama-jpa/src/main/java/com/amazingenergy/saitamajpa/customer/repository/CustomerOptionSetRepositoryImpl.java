package com.amazingenergy.saitamajpa.customer.repository;

import com.amazingenergy.corejpa.repository.BaseRepositoryImpl;
import com.amazingenergy.saitamadomain.customer.domain.attribute.CustomerOption;
import com.amazingenergy.saitamadomain.customer.domain.attribute.CustomerOptionSet;
import com.amazingenergy.saitamadomain.customer.domain.attribute.CustomerOptionValue;
import com.amazingenergy.saitamadomain.customer.repository.CustomerOptionSetRepository;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import com.amazingenergy.saitamadomain.reference.domain.Language;
import com.amazingenergy.saitamajpa.customer.CustomerMapper;
import com.amazingenergy.saitamajpa.customer.entity.attribute.CustomerOptionSetEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class CustomerOptionSetRepositoryImpl extends BaseRepositoryImpl<UUID, CustomerOptionSet, CustomerOptionSetEntity> implements CustomerOptionSetRepository {

    private final JpaCustomerOptionSetRepository jpaRepository;
    private final CustomerMapper mapper;

    public CustomerOptionSetRepositoryImpl(JpaCustomerOptionSetRepository jpaRepository, CustomerMapper mapper) {
        super(jpaRepository);
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public List<CustomerOptionSet> listByStore(MerchantStore store, Language language) {
        return jpaRepository.findByStore(store.getId(), language.getId())
                .stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public List<CustomerOptionSet> listByOption(CustomerOption option, MerchantStore store) {
        return jpaRepository.findByOptionId(store.getId(), option.getId())
                .stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public List<CustomerOptionSet> listByOptionValue(CustomerOptionValue optionValue, MerchantStore store) {
        return jpaRepository.findByOptionValueId(store.getId(), optionValue.getId())
                .stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public CustomerOptionSetEntity to(CustomerOptionSet source) {
        return mapper.to(source);
    }

    @Override
    public CustomerOptionSet from(CustomerOptionSetEntity destination) {
        return mapper.from(destination);
    }
}
