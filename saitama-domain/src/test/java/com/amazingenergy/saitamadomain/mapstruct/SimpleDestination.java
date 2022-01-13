package com.amazingenergy.saitamadomain.mapstruct;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@AllArgsConstructor
public class SimpleDestination{
    private UUID destinationId;
    private String name;
    private String description;
    private EmbeddableAuditSection auditSection;
    private List<EmbeddableAuditSection> auditSectionList;
}
