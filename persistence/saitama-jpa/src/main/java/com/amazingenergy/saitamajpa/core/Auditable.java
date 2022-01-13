package com.amazingenergy.saitamajpa.core;

public interface Auditable {
    EmbeddableAuditSection getAuditSection();

    void setAuditSection(EmbeddableAuditSection audit);
}
