package com.amazingenergy.saitamajpa.catalog.product.entity.attribute;

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
@Table(name = "ProductOption",
        indexes = {@Index(name = "PRD_OPTION_CODE_IDX", columnList = "Code")},
        uniqueConstraints = @UniqueConstraint(columnNames = {"MerchantId", "Code"}))
public class ProductOptionEntity {
    @Id
    @Column(name = "Id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "Code")
    private String code;

    @Column(name = "SortOrder")
    private Integer sortOrder;

    @Column(name = "Type", length = 10)
    private String type;


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "productOption")
    private Set<ProductOptionDescriptionEntity> descriptions = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MerchantId", nullable = false)
    private MerchantStoreEntity merchantStore;

    @Column(name = "IsReadOnly")
    private boolean readOnly;
}
