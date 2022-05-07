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
@Table(name="ProductOptionValue",
        indexes = { @Index(name="PRD_OPTION_VAL_CODE_IDX", columnList = "Code")},
        uniqueConstraints= @UniqueConstraint(columnNames = {"MerchantId", "Code"}))
public class ProductOptionValueEntity {
    @Id
    @Column(name = "Id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name="SortOrder")
    private Integer sortOrder;

    @Column(name="Image")
    private String image;

    @Column(name="DisplayOnly")
    private boolean displayOnly;

    @Column(name="Code")
    private String code;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "productOptionValue")
    private Set<ProductOptionValueDescriptionEntity> descriptions = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MerchantId", nullable=false)
    private MerchantStoreEntity merchantStore;
}
