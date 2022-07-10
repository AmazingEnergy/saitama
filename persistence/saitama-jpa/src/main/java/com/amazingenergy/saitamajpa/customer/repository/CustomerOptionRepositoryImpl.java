package com.amazingenergy.saitamajpa.customer.repository;

import com.amazingenergy.corejpa.repository.BaseRepositoryImpl;
import com.amazingenergy.saitamadomain.customer.domain.attribute.CustomerOption;
import com.amazingenergy.saitamadomain.customer.repository.CustomerAttributeRepository;
import com.amazingenergy.saitamadomain.customer.repository.CustomerOptionRepository;
import com.amazingenergy.saitamadomain.customer.repository.CustomerOptionSetRepository;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import com.amazingenergy.saitamadomain.reference.domain.Language;
import com.amazingenergy.saitamajpa.customer.CustomerMapper;
import com.amazingenergy.saitamajpa.customer.entity.attribute.CustomerOptionEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class CustomerOptionRepositoryImpl extends BaseRepositoryImpl<UUID, CustomerOption, CustomerOptionEntity> implements CustomerOptionRepository {

    private final JpaCustomerOptionRepository jpaRepository;
    private final CustomerAttributeRepository attributeRepository;
    private final CustomerOptionSetRepository optionSetRepository;
    private final CustomerMapper mapper;

    public CustomerOptionRepositoryImpl(JpaCustomerOptionRepository jpaRepository,
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
    public List<CustomerOption> listByStore(MerchantStore store, Language language) {
        return jpaRepository.findByStore(store.getId(), language.getId())
                .stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public Optional<CustomerOption> getByCode(MerchantStore store, String optionCode) {
        return jpaRepository.findByCode(store.getId(), optionCode).map(this::from);
    }

    @Override
    public void delete(CustomerOption customerOption) {
        var attributes = attributeRepository
                .getByOptionId(customerOption.getMerchantStore(), customerOption.getId());
        for (var attribute : attributes) {
            attributeRepository.delete(attribute);
        }

        var optionSets = optionSetRepository
                .listByOption(customerOption, customerOption.getMerchantStore());
        for (var optionSet : optionSets) {
            optionSetRepository.delete(optionSet);
        }

        super.delete(customerOption);
    }

    @Override
    public CustomerOptionEntity to(CustomerOption source) {
        return mapper.to(source);
    }

    @Override
    public CustomerOption from(CustomerOptionEntity destination) {
        return mapper.from(destination);
    }
}
