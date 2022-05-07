package com.amazingenergy.saitamajpa.catalog.catalog.entity;

import com.amazingenergy.corejpa.audit.AuditListener;
import com.amazingenergy.corejpa.audit.Auditable;
import com.amazingenergy.corejpa.audit.EmbeddableAuditSection;
import com.amazingenergy.saitamajpa.catalog.category.entity.CategoryEntity;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
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
