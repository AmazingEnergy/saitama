package com.amazingenergy.saitamajpa.reference.entity;

import com.amazingenergy.corejpa.audit.AuditListener;
import com.amazingenergy.corejpa.audit.Auditable;
import com.amazingenergy.corejpa.audit.EmbeddableAuditSection;
import com.amazingenergy.saitamajpa.merchant.entity.MerchantStoreEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(value = AuditListener.class)
@Table(name = "Language", indexes = {@Index(name = "CodeIdx2", columnList = "Code")})
@Cacheable
public class LanguageEntity implements Auditable {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Embedded
    private EmbeddableAuditSection auditSection;

    @Column(name = "Code", nullable = false)
    private String code;

    @Column(name = "SortOrder")
    private Integer sortOrder;

    @OneToMany(mappedBy = "defaultLanguage", targetEntity = MerchantStoreEntity.class)
    private List<MerchantStoreEntity> storesDefaultLanguage;

    @ManyToMany(mappedBy = "languages", targetEntity = MerchantStoreEntity.class, fetch = FetchType.LAZY)
    private List<MerchantStoreEntity> stores = new ArrayList<MerchantStoreEntity>();
}
