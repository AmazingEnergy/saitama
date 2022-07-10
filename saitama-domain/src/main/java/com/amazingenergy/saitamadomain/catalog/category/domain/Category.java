package com.amazingenergy.saitamadomain.catalog.category.domain;

import com.amazingenergy.core.Notification;
import com.amazingenergy.core.domain.AggregateRoot;
import com.amazingenergy.core.domain.AuditSection;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import com.amazingenergy.saitamadomain.reference.domain.Language;
import com.amazingenergy.saitamadomain.seedwork.constant.Constants;
import lombok.*;

import java.util.*;

@Data
@EqualsAndHashCode(callSuper = true)
public class Category extends AggregateRoot<UUID, Category> {

    public static final String CATEGORY_MUST_HAVE_MERCHANT = "MerchantStore is required in Category {}!";

    private String code;
    private boolean categoryStatus;
    private int depth;
    private String categoryImage;
    private Category parent;
    private List<Category> categories = new ArrayList<>();
    private MerchantStore merchantStore;
    private Set<CategoryDescription> descriptions = new HashSet<>();
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

        // init as root
        this.parent = null;
        this.depth = 0;
        this.lineage = Constants.SLASH + this.id + Constants.SLASH;
    }

    public Notification addChild(Category child) {
        var note = Notification.instance();

        if (child.getMerchantStore() == null)
            return note.addErrorCode("CATEGORY_MUST_HAVE_MERCHANT", CATEGORY_MUST_HAVE_MERCHANT, child.getCode());

        child.setParent(this);
        child.setDepth(this.depth + 1);
        child.setLineage(this.lineage + Constants.SLASH + child.getId() + Constants.SLASH);
        return note;
    }

    public Optional<CategoryDescription> getDescription(Language language) {
        return descriptions.stream().filter(d -> d.getLanguage().equals(language)).findFirst();
    }
}
