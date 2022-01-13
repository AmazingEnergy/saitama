package com.amazingenergy.saitamadomain.catalog.product.repository;

import com.amazingenergy.core.repository.Repository;
import com.amazingenergy.saitamadomain.catalog.product.domain.Product;

import java.util.UUID;

public interface ProductRepository extends Repository<Product> {
    Iterable<Product> findAll();
    Product findById(UUID id);
}
