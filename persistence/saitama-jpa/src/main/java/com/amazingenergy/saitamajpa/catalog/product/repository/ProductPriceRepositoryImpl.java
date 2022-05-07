package com.amazingenergy.saitamajpa.catalog.product.repository;

import com.amazingenergy.corejpa.repository.BaseRepositoryImpl;
import com.amazingenergy.saitamadomain.catalog.product.domain.price.ProductPrice;
import com.amazingenergy.saitamadomain.catalog.product.repository.ProductPriceRepository;
import com.amazingenergy.saitamajpa.catalog.product.ProductMapper;
import com.amazingenergy.saitamajpa.catalog.product.entity.price.ProductPriceEntity;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class ProductPriceRepositoryImpl extends BaseRepositoryImpl<UUID, ProductPrice, ProductPriceEntity> implements ProductPriceRepository {

    private final ProductMapper mapper;

    public ProductPriceRepositoryImpl(JpaProductPriceRepository jpaRepository, ProductMapper mapper) {
        super(jpaRepository);
        this.mapper = mapper;
    }

    @Override
    public ProductPriceEntity to(ProductPrice source) {
        return mapper.to(source);
    }

    @Override
    public ProductPrice from(ProductPriceEntity destination) {
        return mapper.from(destination);
    }
}
