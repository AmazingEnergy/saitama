package com.amazingenergy.saitamadomain.catalog.category.repository;


import com.amazingenergy.core.repository.Repository;
import com.amazingenergy.saitamadomain.catalog.category.domain.Category;

import java.util.UUID;

public interface CategoryRepository extends Repository<Category> {
    Iterable<Category> findAll();

    Category findById(UUID id);
}
