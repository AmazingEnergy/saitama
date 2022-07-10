package com.amazingenergy.saitamajpa.catalog.catalog.entity;

import com.amazingenergy.corejpa.audit.AuditListener;
import com.amazingenergy.corejpa.audit.Auditable;
import com.amazingenergy.corejpa.audit.EmbeddableAuditSection;
import com.amazingenergy.saitamajpa.merchant.entity.MerchantStoreEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(value = AuditListener.class)
@Table(name = "Catalog",
        uniqueConstraints = @UniqueConstraint(columnNames = {"MerchantId", "Code"}))
public class CatalogEntity implements Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Embedded
    private EmbeddableAuditSection auditSection;

    @OneToMany(mappedBy = "catalog", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<CatalogCategoryEntryEntity> entry = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MerchantId", nullable = false)
    private MerchantStoreEntity merchantStore;

    @Column(name = "Visible")
    private boolean visible;

    @Column(name = "DefaultCatalog")
    private boolean defaultCatalog;

    @Column(name = "Code", length = 100, nullable = false)
    private String code;

    @Column(name = "SortOrder")
    private Integer sortOrder = 0;
}
