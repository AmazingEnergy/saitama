package com.amazingenergy.saitamajpa.catalog.catalog.repository;

import com.amazingenergy.saitamajpa.catalog.catalog.entity.CatalogCategoryEntryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaCatalogEntryRepository extends JpaRepository<CatalogCategoryEntryEntity, UUID> {
    @Query(value = "select distinct c from CatalogCategoryEntryEntity c  "
            + "join fetch c.category cc "
            + "join fetch c.catalog cl "
            + "join fetch cl.merchantStore clm "
            + "left join fetch cc.descriptions ccd "
            + "where cl.id=?1 and clm.id=?2 and ccd.language.id=?3",
            countQuery = "select  count(c) from CatalogCategoryEntryEntity c " +
                    "join c.category cc " +
                    "join c.catalog cl " +
                    "join cl.merchantStore clm " +
                    "join cc.descriptions ccd " +
                    "where cl.id=?1 and clm.id=?2 and ccd.language.id=?3")
    Page<CatalogCategoryEntryEntity> listByCatalog(UUID catalogId, UUID storeId, UUID languageId, Pageable pageable);
}
