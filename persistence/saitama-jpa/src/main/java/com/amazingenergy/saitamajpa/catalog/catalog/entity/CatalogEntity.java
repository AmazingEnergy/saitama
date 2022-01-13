package com.amazingenergy.saitamajpa.catalog.catalog.entity;

import com.amazingenergy.saitamadomain.constants.SchemaConstant;
import com.amazingenergy.saitamajpa.core.AuditListener;
import com.amazingenergy.saitamajpa.core.Auditable;
import com.amazingenergy.saitamajpa.core.EmbeddableAuditSection;
import com.amazingenergy.saitamajpa.merchant.entity.MerchantStoreEntity;
import lombok.*;

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
        uniqueConstraints=@UniqueConstraint(columnNames = {"MerchantId", "Code"}))
public class CatalogEntity implements Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,
            generator = "TableGen")
    @TableGenerator(name = "TableGen",
            table = "SmSequencer",
            pkColumnName = "SeqName",
            valueColumnName = "SeqCount",
            pkColumnValue = "CatalogSeqNextVal",
            allocationSize = SchemaConstant.DESCRIPTION_ID_ALLOCATION_SIZE,
            initialValue = SchemaConstant.DESCRIPTION_ID_START_VALUE
    )
    private UUID id;

    @Embedded
    private EmbeddableAuditSection auditSection;

    @OneToMany(mappedBy="catalog", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<CatalogCategoryEntryEntity> entry = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MerchantId", nullable=false)
    private MerchantStoreEntity merchantStore;

    @Column(name = "Visible")
    private boolean visible;

    @Column(name="DefaultCatalog")
    private boolean defaultCatalog;

    @Column(name="Code", length=100, nullable=false)
    private String code;

    @Column(name = "SortOrder")
    private Integer sortOrder = 0;
}
