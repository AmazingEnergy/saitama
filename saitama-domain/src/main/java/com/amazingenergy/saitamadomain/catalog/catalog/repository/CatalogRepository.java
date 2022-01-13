package com.amazingenergy.saitamadomain.catalog.catalog.repository;

import com.amazingenergy.core.repository.Repository;
import com.amazingenergy.saitamadomain.catalog.catalog.domain.Catalog;

import java.util.UUID;

public interface CatalogRepository extends Repository<Catalog> {
    Iterable<Catalog> findAll();

    Catalog findById(UUID id);
}
