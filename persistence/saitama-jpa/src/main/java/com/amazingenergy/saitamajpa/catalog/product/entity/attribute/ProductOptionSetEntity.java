package com.amazingenergy.saitamajpa.catalog.product.entity.attribute;

import com.amazingenergy.saitamajpa.catalog.product.entity.type.ProductTypeEntity;
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
@Table(name = "ProductOptionSet", uniqueConstraints = {@UniqueConstraint(columnNames = {"MerchantId", "Code"})})
public class ProductOptionSetEntity {
    @Id
    @Column(name = "Id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "Code")
    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ProductOptionId", nullable = false)
    private ProductOptionEntity option;

    @ManyToMany(fetch = FetchType.LAZY, targetEntity = ProductOptionValueEntity.class)
    @JoinTable(name = "ProductOptionSetProductOptionValue")
    private List<ProductOptionValueEntity> values = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, targetEntity = ProductTypeEntity.class)
    @JoinTable(name = "ProductOptionSetProductType")
    private Set<ProductTypeEntity> productTypes = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MerchantId", nullable = false)
    private MerchantStoreEntity store;

    @Column(name = "DisplayOnly")
    private boolean displayOnly = false;
}
