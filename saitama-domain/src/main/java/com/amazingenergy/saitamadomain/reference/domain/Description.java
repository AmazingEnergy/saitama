package com.amazingenergy.saitamadomain.reference.domain;

import com.amazingenergy.core.domain.AuditSection;
import com.amazingenergy.core.domain.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class Description extends Entity<UUID, Description> {
    private String name;
    private String title;
    private String description;
    private AuditSection auditSection;
    private Language language;

    private Description() {
        super(UUID.randomUUID());
    }

    public Description(UUID id, String name, String title, String description, AuditSection auditSection, Language language) {
        super(id);
        this.name = name;
        this.title = title;
        this.description = description;
        this.auditSection = auditSection;
        this.language = language;
    }
}
