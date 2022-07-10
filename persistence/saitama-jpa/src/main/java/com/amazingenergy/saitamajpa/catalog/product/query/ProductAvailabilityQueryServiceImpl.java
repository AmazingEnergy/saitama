package com.amazingenergy.saitamajpa.catalog.product.query;

import com.amazingenergy.saitamaappservice.catalog.product.query.ProductAvailabilityQueryService;
import com.amazingenergy.saitamadomain.catalog.product.domain.Product;
import com.amazingenergy.saitamadomain.catalog.product.domain.availability.ProductAvailability;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import com.amazingenergy.saitamajpa.catalog.product.ProductMapper;
import com.amazingenergy.saitamajpa.catalog.product.repository.JpaProductAvailabilityRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductAvailabilityQueryServiceImpl implements ProductAvailabilityQueryService {

    private final JpaProductAvailabilityRepository jpaRepository;
    private final ProductMapper mapper;

    public ProductAvailabilityQueryServiceImpl(JpaProductAvailabilityRepository jpaRepository, ProductMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Page<ProductAvailability> listByProduct(Product product, MerchantStore store, String child, Pageable pageable) {
        return jpaRepository.listByStore(product.getId(), store.getId(), child, pageable).map(mapper::from);
    }
}
