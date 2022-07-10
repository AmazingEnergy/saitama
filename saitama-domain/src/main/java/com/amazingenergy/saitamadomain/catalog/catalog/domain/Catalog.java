package com.amazingenergy.saitamadomain.catalog.catalog.domain;

import com.amazingenergy.core.Notification;
import com.amazingenergy.core.domain.AggregateRoot;
import com.amazingenergy.core.domain.AuditSection;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Allows grouping products and category
 * Catalog
 * - category 1
 * - category 2
 * - product 1
 * - product 2
 * - product 3
 * - product 4
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Catalog extends AggregateRoot<UUID, Catalog> {

    private static final String CATEGORY_ENTRY_IS_ALREADY_ASSIGNED = "CatalogEntry Id:{0} had already assigned";

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

    public Catalog(String code, String name, MerchantStore merchantStore, AuditSection auditSection) {
        super(UUID.randomUUID());
        this.code = code;
        this.name = name;
        this.merchantStore = merchantStore;
        this.auditSection = auditSection;
    }

    public Notification addEntries(Set<CatalogCategoryEntry> entries) {
        var notification = Notification.instance();

        if (this.entries == null)
            this.entries = new HashSet<>();
        else {
            var existedEntry = entries.stream().filter(entry -> this.entries.contains(entry)).findFirst();
            if (existedEntry.isPresent()) {
                notification.addErrorCode("CATEGORY_ENTRY_IS_ALREADY_ASSIGNED", CATEGORY_ENTRY_IS_ALREADY_ASSIGNED, existedEntry.get().getId());
                return notification;
            }
        }

        this.entries.addAll(entries);
        return notification;
    }
}
