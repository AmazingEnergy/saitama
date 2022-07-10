package com.amazingenergy.saitamadomain.catalog.category.domain;

import com.amazingenergy.saitamadomain.common.Description;
import com.amazingenergy.saitamadomain.reference.domain.Language;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CategoryDescription extends Description {
    private String seUrl;
    private String categoryHighlight;
    private String metaTagTitle;
    private String metaTagKeywords;
    private String metaTagDescription;

    public CategoryDescription() {
    }

    public CategoryDescription(String name, Language language) {
        this.setName(name);
        this.setLanguage(language);
    }
}
