package com.amazingenergy.saitamajpa.customer.repository;

import com.amazingenergy.corejpa.repository.BaseRepositoryImpl;
import com.amazingenergy.saitamadomain.customer.domain.attribute.CustomerOptionValue;
import com.amazingenergy.saitamadomain.customer.repository.CustomerAttributeRepository;
import com.amazingenergy.saitamadomain.customer.repository.CustomerOptionSetRepository;
import com.amazingenergy.saitamadomain.customer.repository.CustomerOptionValueRepository;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import com.amazingenergy.saitamadomain.reference.domain.Language;
import com.amazingenergy.saitamajpa.customer.CustomerMapper;
import com.amazingenergy.saitamajpa.customer.entity.attribute.CustomerOptionValueEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class CustomerOptionValueRepositoryImpl extends BaseRepositoryImpl<UUID, CustomerOptionValue, CustomerOptionValueEntity> implements CustomerOptionValueRepository {

    private final JpaCustomerOptionValueRepository jpaRepository;
    private final CustomerAttributeRepository attributeRepository;
    private final CustomerOptionSetRepository optionSetRepository;
    private final CustomerMapper mapper;

    public CustomerOptionValueRepositoryImpl(JpaCustomerOptionValueRepository jpaRepository,
                                             CustomerAttributeRepository attributeRepository,
                                             CustomerOptionSetRepository optionSetRepository,
                                             CustomerMapper mapper) {
        super(jpaRepository);
        this.jpaRepository = jpaRepository;
        this.attributeRepository = attributeRepository;
        this.optionSetRepository = optionSetRepository;
        this.mapper = mapper;
    }

    @Override
    public List<CustomerOptionValue> listByStore(MerchantStore store, Language language) {
        return jpaRepository.findByStore(store.getId(), language.getId())
                .stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public Optional<CustomerOptionValue> getByCode(MerchantStore store, String optionValueCode) {
        return jpaRepository.findByCode(store.getId(), optionValueCode).map(this::from);
    }

    @Override
    public void delete(CustomerOptionValue customerOption) {
        var attributes = attributeRepository
                .getByOptionId(customerOption.getMerchantStore(), customerOption.getId());
        for (var attribute : attributes) {
            attributeRepository.delete(attribute);
        }

        var optionSets = optionSetRepository
                .listByOptionValue(customerOption, customerOption.getMerchantStore());
        for (var optionSet : optionSets) {
            optionSetRepository.delete(optionSet);
        }

        super.delete(customerOption);
    }

    @Override
    public CustomerOptionValueEntity to(CustomerOptionValue source) {
        return mapper.to(source);
    }

    @Override
    public CustomerOptionValue from(CustomerOptionValueEntity destination) {
        return mapper.from(destination);
    }
}
