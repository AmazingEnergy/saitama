package com.amazingenergy.corejpa;

import com.amazingenergy.core.domain.AuditSection;
import com.amazingenergy.corejpa.audit.EmbeddableAuditSection;
import org.mapstruct.Mapper;

@Mapper
public interface CoreJpaMapper {
    EmbeddableAuditSection to(AuditSection source);

    AuditSection from(EmbeddableAuditSection destination);
}
