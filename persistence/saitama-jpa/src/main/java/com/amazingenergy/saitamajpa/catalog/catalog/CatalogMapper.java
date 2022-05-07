package com.amazingenergy.saitamajpa.catalog.catalog;

import com.amazingenergy.saitamadomain.catalog.catalog.domain.Catalog;
import com.amazingenergy.saitamadomain.catalog.catalog.domain.CatalogCategoryEntry;
import com.amazingenergy.saitamajpa.catalog.catalog.entity.CatalogCategoryEntryEntity;
import com.amazingenergy.saitamajpa.catalog.catalog.entity.CatalogEntity;
import org.mapstruct.Mapper;

@Mapper
public interface CatalogMapper {
    CatalogEntity to(Catalog source);

    Catalog from(CatalogEntity destination);

    CatalogCategoryEntryEntity to(CatalogCategoryEntry source);

    CatalogCategoryEntry from(CatalogCategoryEntryEntity destination);
}
