package com.amazingenergy.saitamadomain.catalog.catalog.domain;

import com.amazingenergy.core.domain.AggregateRoot;
import com.amazingenergy.core.domain.AuditSection;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Allows grouping products and category
 *  Catalog
 *      - category 1
 *      - category 2
 *
 *      - product 1
 *      - product 2
 *      - product 3
 *      - product 4
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Catalog extends AggregateRoot<UUID, Catalog> {
    private String code;
    private String name;
    private int sortOrder = 0;
    private Set<CatalogCategoryEntry> entries = new HashSet<>();
    private MerchantStore merchantStore;
    private AuditSection auditSection;
    private boolean visible = true;
    private boolean defaultCatalog = false;

    private Catalog() {
        super(UUID.randomUUID());
    }

    public Catalog(UUID id, String code, String name, Set<CatalogCategoryEntry> entries, MerchantStore merchantStore, AuditSection auditSection) {
        super(id);
        this.code = code;
        this.name = name;
        this.entries = entries;
        this.merchantStore = merchantStore;
        this.auditSection = auditSection;
    }
}
