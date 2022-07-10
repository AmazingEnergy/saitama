package com.amazingenergy.saitamadomain.common;

import com.amazingenergy.core.domain.AuditSection;
import com.amazingenergy.core.domain.Entity;
import com.amazingenergy.saitamadomain.reference.domain.Language;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class Description extends Entity<UUID, Description> {
    protected String name;
    protected String title;
    protected String description;
    protected AuditSection auditSection;
    protected Language language;

    public Description() {
        super(UUID.randomUUID());
    }

    public Description(Language language, String name) {
        super(UUID.randomUUID());
        this.setLanguage(language);
        this.setName(name);
    }
}
