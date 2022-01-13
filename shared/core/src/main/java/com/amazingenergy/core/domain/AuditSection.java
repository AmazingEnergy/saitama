package com.amazingenergy.core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuditSection implements Serializable {
    private Date dateCreated;
    private String createdBy;
    private Date dateModified;
    private String modifiedBy;

    public static AuditSection getInstance() {
        return new AuditSection(new Date(), "System", new Date(), "System");
    }

    public static AuditSection getInstance(String createdBy, String modifiedBy) {
        return new AuditSection(new Date(), createdBy, new Date(), modifiedBy);
    }
}
