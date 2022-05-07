package com.amazingenergy.saitamajpa.catalog.product.repository;

import com.amazingenergy.corejpa.repository.BaseRepositoryImpl;
import com.amazingenergy.saitamadomain.catalog.product.domain.Product;
import com.amazingenergy.saitamadomain.catalog.product.domain.availability.ProductAvailability;
import com.amazingenergy.saitamadomain.catalog.product.repository.ProductAvailabilityRepository;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import com.amazingenergy.saitamajpa.catalog.product.ProductMapper;
import com.amazingenergy.saitamajpa.catalog.product.entity.availability.ProductAvailabilityEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class ProductAvailabilityRepositoryImpl extends BaseRepositoryImpl<UUID, ProductAvailability, ProductAvailabilityEntity> implements ProductAvailabilityRepository {

    private final JpaProductAvailabilityRepository jpaRepository;
    private final ProductMapper mapper;

    public ProductAvailabilityRepositoryImpl(JpaProductAvailabilityRepository jpaRepository, ProductMapper mapper) {
        super(jpaRepository);
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public void saveOrUpdate(ProductAvailability availability) {
        jpaRepository.save(this.to(availability));
    }

    @Override
    public Optional<ProductAvailability> getByStore(Product product, MerchantStore store) {
        return jpaRepository.getByStore(product.getId(), store.getCode()).map(this::from);
    }

    @Override
    public Optional<ProductAvailability> getById(UUID availabilityId, MerchantStore store) {
        return jpaRepository.getById(availabilityId, store.getId()).map(this::from);
    }

    @Override
    public Optional<ProductAvailability> getByInventoryId(UUID productId, UUID availabilityId, MerchantStore store) {
        return jpaRepository.getByStore(productId, availabilityId).map(this::from);
    }

    @Override
    public int count(Product product) {
        return jpaRepository.count(product.getId());
    }

    @Override
    public ProductAvailabilityEntity to(ProductAvailability source) {
        return mapper.to(source);
    }

    @Override
    public ProductAvailability from(ProductAvailabilityEntity destination) {
        return mapper.from(destination);
    }
}
