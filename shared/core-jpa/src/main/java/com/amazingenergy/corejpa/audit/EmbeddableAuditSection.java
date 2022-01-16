package com.amazingenergy.corejpa.audit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class EmbeddableAuditSection {
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DateCreated")
    private Date dateCreated;

    @Column(name = "CreatedBy", length = 60)
    private String createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DateModified")
    private Date dateModified;

    @Column(name = "ModifiedBy", length = 60)
    private String modifiedBy;
}
