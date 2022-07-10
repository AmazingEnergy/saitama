package com.amazingenergy.corejpa.audit;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

public class AuditListener {
    @PrePersist
    public void onSave(Object o) {
        updateDateModified(o);
    }

    @PreUpdate
    public void onUpdate(Object o) {
        updateDateModified(o);
    }

    private void updateDateModified(Object o) {
        if (o instanceof Auditable) {
            Auditable audit = (Auditable) o;
            var auditSection = audit.getAuditSection();

            auditSection.setDateModified(new Date());
            if (auditSection.getDateCreated() == null) {
                auditSection.setDateCreated(new Date());
            }
            audit.setAuditSection(auditSection);
        }
    }

}
