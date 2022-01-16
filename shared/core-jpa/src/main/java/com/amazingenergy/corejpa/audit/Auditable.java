package com.amazingenergy.corejpa.audit;

public interface Auditable {
    EmbeddableAuditSection getAuditSection();

    void setAuditSection(EmbeddableAuditSection audit);
}
