package com.amazingenergy.saitamajpa.core;

import com.amazingenergy.core.domain.AuditSection;
import org.mapstruct.Mapper;

@Mapper
public interface CoreMapper {
    EmbeddableAuditSection auditSectionToEmbeddableAuditSection(AuditSection source);
    AuditSection embeddableAuditSectionToAuditSection(EmbeddableAuditSection destination);
}
