package com.amazingenergy.saitamajpa.reference.entity;

import com.amazingenergy.saitamajpa.core.AuditListener;
import com.amazingenergy.saitamajpa.core.Auditable;
import com.amazingenergy.saitamajpa.core.EmbeddableAuditSection;
import com.amazingenergy.saitamajpa.merchant.entity.MerchantStoreEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(value = AuditListener.class)
@Table(name = "Language", indexes = { @Index(name="CodeIdx2", columnList = "Code")})
@Cacheable
public class LanguageEntity implements Auditable {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.TABLE,
            generator = "TableGen")
    @TableGenerator(name = "TableGen",
            table = "SmSequencer",
            pkColumnName = "SeqName",
            valueColumnName = "SeqCount",
            pkColumnValue = "LangSeqNextVal")
    private Integer id;

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
