package com.amazingenergy.saitamajpa.catalog.category.entity;

import com.amazingenergy.corejpa.audit.AuditListener;
import com.amazingenergy.corejpa.audit.Auditable;
import com.amazingenergy.corejpa.audit.EmbeddableAuditSection;
import com.amazingenergy.saitamajpa.merchant.entity.MerchantStoreEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(value = AuditListener.class)
@Table(name = "Category",uniqueConstraints=
@UniqueConstraint(columnNames = {"MerchantId", "Code"}) )
public class CategoryEntity implements Auditable {
    @Id
    @Column(name = "Id", unique=true, nullable=false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Embedded
    private EmbeddableAuditSection auditSection;

    @OneToMany(mappedBy="category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<CategoryDescriptionEntity> descriptions = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MerchantId", nullable=false)
    private MerchantStoreEntity merchantStore;

    @ManyToOne
    @JoinColumn(name = "ParentId")
    private CategoryEntity parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.REMOVE)
    private List<CategoryEntity> categories = new ArrayList<>();

    @Column(name = "Image", length=100)
    private String categoryImage;

    @Column(name = "SortOrder")
    private Integer sortOrder = 0;

    @Column(name = "Status")
    private boolean categoryStatus;

    @Column(name = "Visible")
    private boolean visible;

    @Column(name = "Depth")
    private Integer depth;

    @Column(name = "Lineage")
    private String lineage;

    @Column(name="Featured")
    private boolean featured;

    @Column(name="Code", length=100, nullable=false)
    private String code;
}
