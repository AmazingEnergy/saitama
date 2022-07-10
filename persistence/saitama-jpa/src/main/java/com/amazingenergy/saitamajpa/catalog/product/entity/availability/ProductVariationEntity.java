package com.amazingenergy.saitamajpa.catalog.product.entity.availability;

import com.amazingenergy.corejpa.audit.AuditListener;
import com.amazingenergy.corejpa.audit.Auditable;
import com.amazingenergy.corejpa.audit.EmbeddableAuditSection;
import com.amazingenergy.saitamajpa.catalog.product.entity.attribute.ProductOptionEntity;
import com.amazingenergy.saitamajpa.catalog.product.entity.attribute.ProductOptionValueEntity;
import com.amazingenergy.saitamajpa.merchant.entity.MerchantStoreEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(value = AuditListener.class)
@Table(name = "ProductVariation",
        uniqueConstraints = @UniqueConstraint(columnNames = {"MerchantId", "OptionId", "OptionValueId"}))
public class ProductVariationEntity implements Auditable {
    @Id
    @Column(name = "Id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name="Code", length=100, nullable=false)
    private String code;

    @Embedded
    private EmbeddableAuditSection auditSection;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MerchantId", nullable=false)
    private MerchantStoreEntity merchantStore;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="OptionId", nullable=false)
    private ProductOptionEntity option;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="OptionValueId", nullable=false)
    private ProductOptionValueEntity optionValue;
}
