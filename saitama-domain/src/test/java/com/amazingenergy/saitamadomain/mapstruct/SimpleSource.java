package com.amazingenergy.saitamadomain.mapstruct;

import com.amazingenergy.core.domain.AggregateRoot;
import com.amazingenergy.core.domain.AuditSection;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class SimpleSource extends AggregateRoot<UUID, SimpleSource> {
    private String name;
    private String description;
    private AuditSection auditSection;
    private List<AuditSection> auditSectionList;

    public SimpleSource(UUID id, String name, String description, AuditSection auditSection, List<AuditSection> auditSectionList) {
        super(id);
        this.name = name;
        this.description = description;
        this.auditSection = auditSection;
        this.auditSectionList = auditSectionList;
    }
}
