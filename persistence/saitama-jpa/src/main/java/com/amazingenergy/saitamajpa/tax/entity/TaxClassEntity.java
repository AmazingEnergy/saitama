package com.amazingenergy.saitamajpa.tax.entity;

import com.amazingenergy.saitamajpa.catalog.product.entity.ProductEntity;
import com.amazingenergy.saitamajpa.merchant.entity.MerchantStoreEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TaxClass",
        indexes = {@Index(name = "TAX_CLASS_CODE_IDX", columnList = "Code")},
        uniqueConstraints = @UniqueConstraint(columnNames = {"MerchantId", "Code"}))
public class TaxClassEntity {
    @Id
    @Column(name = "Id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "Code", nullable = false, length = 10)
    private String code;

    @Column(name = "Title", nullable = false, length = 32)
    private String title;

    @OneToMany(mappedBy = "taxClass", targetEntity = ProductEntity.class)
    private List<ProductEntity> products = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MerchantId", nullable = true)
    private MerchantStoreEntity merchantStore;

    @OneToMany(mappedBy = "taxClass")
    private List<TaxRateEntity> taxRates = new ArrayList<>();
}
