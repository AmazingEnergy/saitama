package com.amazingenergy.saitamadomain.catalog.category.domain;

import com.amazingenergy.core.domain.AggregateRoot;
import com.amazingenergy.core.domain.AuditSection;
import com.amazingenergy.saitamadomain.reference.domain.Description;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import lombok.*;

import java.util.*;

@Data
@EqualsAndHashCode(callSuper = true)
public class Category extends AggregateRoot<UUID, Category> {
    private String code;
    private boolean categoryStatus;
    private int depth;
    private String categoryImage;
    private Category parent;
    private List<Category> categories = new ArrayList<>();
    private MerchantStore merchantStore;
    private Set<Description> descriptions = new HashSet<>();
    private AuditSection auditSection;
    private String lineage;
    private boolean featured;
    private int sortOrder = 0;
    private boolean visible = true;

    private Category() {
        super(UUID.randomUUID());
    }

    public Category(UUID id, String code, MerchantStore merchantStore) {
        super(id);
        this.code = code;
        this.merchantStore = merchantStore;
    }
}
