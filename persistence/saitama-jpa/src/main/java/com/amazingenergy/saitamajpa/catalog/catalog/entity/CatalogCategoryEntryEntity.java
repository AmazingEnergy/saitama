package com.amazingenergy.saitamajpa.catalog.catalog.entity;

import com.amazingenergy.saitamadomain.constants.SchemaConstant;
import com.amazingenergy.saitamajpa.catalog.category.entity.CategoryEntity;
import com.amazingenergy.saitamajpa.core.AuditListener;
import com.amazingenergy.saitamajpa.core.Auditable;
import com.amazingenergy.saitamajpa.core.EmbeddableAuditSection;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(value = AuditListener.class)
@Table(name = "CatalogEntry", uniqueConstraints =
@UniqueConstraint(columnNames = {"CategoryId", "CatalogId"}))
public class CatalogCategoryEntryEntity implements Auditable {
    @Embedded
    private EmbeddableAuditSection auditSection;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,
            generator = "TableGen")
    @TableGenerator(name = "TableGen",
            table = "SmSequencer",
            pkColumnName = "SeqName",
            valueColumnName = "SeqCount",
            allocationSize = SchemaConstant.DESCRIPTION_ID_ALLOCATION_SIZE,
            initialValue = SchemaConstant.DESCRIPTION_ID_START_VALUE,
            pkColumnValue = "CatalogEntSeqNextVal")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "CategoryId", nullable = false)
    CategoryEntity category;

    @ManyToOne
    @JoinColumn(name = "CatalogId", nullable = false)
    private CatalogEntity catalog;

    @Column(name = "Visible")
    private boolean visible;
}
