package com.amazingenergy.saitamadomain.catalog.catalog.domain;

import com.amazingenergy.core.domain.AuditSection;
import com.amazingenergy.core.domain.Entity;
import com.amazingenergy.saitamadomain.catalog.category.domain.Category;
import lombok.*;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class CatalogCategoryEntry extends Entity<UUID, CatalogCategoryEntry> {
    private Category category;
    private AuditSection auditSection;
    private boolean visible = true;

    private CatalogCategoryEntry() {
        super(UUID.randomUUID());
    }

    public CatalogCategoryEntry(UUID id, Category category, AuditSection auditSection) {
        super(id);
        this.category = category;
        this.auditSection = auditSection;
    }
}
