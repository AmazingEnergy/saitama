package com.amazingenergy.saitamadomain.catalog.catalog.repository;

import com.amazingenergy.core.repository.EntityRepository;
import com.amazingenergy.saitamadomain.catalog.catalog.domain.CatalogCategoryEntry;

import java.util.UUID;

public interface CatalogEntryRepository extends EntityRepository<UUID, CatalogCategoryEntry> {
    void remove(CatalogCategoryEntry catalogEntry);
}
