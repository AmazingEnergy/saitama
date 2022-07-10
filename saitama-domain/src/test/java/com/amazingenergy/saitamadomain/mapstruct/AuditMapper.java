package com.amazingenergy.saitamadomain.mapstruct;

import com.amazingenergy.core.domain.AuditSection;
import org.mapstruct.Mapper;

@Mapper
public interface AuditMapper {

    EmbeddableAuditSection auditSectionToEmbeddableAuditSection(AuditSection source);

    AuditSection embeddableAuditSectionToAuditSection(EmbeddableAuditSection destination);
}
