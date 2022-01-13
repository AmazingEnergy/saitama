package com.amazingenergy.saitamadomain.mapstruct;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmbeddableAuditSection {
    private Date dateCreated;
    private String createdBy;
    private Date dateModified;
    private String modifiedBy;
}
