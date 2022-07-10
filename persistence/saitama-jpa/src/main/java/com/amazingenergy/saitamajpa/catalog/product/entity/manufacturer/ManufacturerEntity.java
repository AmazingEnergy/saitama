package com.amazingenergy.saitamajpa.catalog.product.entity.manufacturer;

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
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(value = AuditListener.class)
@Table(name = "ManufacturerId", uniqueConstraints =
@UniqueConstraint(columnNames = {"MerchantId", "Code"}))
public class ManufacturerEntity implements Auditable {
    @Id
    @Column(name = "Id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "Code", length = 100, nullable = false)
    private String code;

    @Embedded
    private EmbeddableAuditSection auditSection;

    @OneToMany(mappedBy = "manufacturer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ManufacturerDescriptionEntity> descriptions = new HashSet<>();

    @Column(name = "Image")
    private String image;

    @Column(name = "SortOrder")
    private Integer order;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MerchantId", nullable = false)
    private MerchantStoreEntity merchantStore;
}
