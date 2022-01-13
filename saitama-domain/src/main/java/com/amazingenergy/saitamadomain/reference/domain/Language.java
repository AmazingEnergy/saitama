package com.amazingenergy.saitamadomain.reference.domain;

import com.amazingenergy.core.domain.AuditSection;
import com.amazingenergy.core.domain.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class Language extends Entity<UUID, Language> {
    private String code;
    private int sortOrder = 0;
    private AuditSection auditSection;

    public Language() {
        super(UUID.randomUUID());
    }

    public Language(UUID id, String code, AuditSection auditSection) {
        super(id);
        this.code = code;
        this.auditSection = auditSection;
    }
}
