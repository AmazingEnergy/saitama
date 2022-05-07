package com.amazingenergy.saitamajpa.catalog.product.entity.relationship;

import com.amazingenergy.saitamajpa.catalog.product.entity.ProductEntity;
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
@Table(name = "ProductRelationship")
public class ProductRelationshipEntity {
    @Id
    @Column(name = "Id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "Code")
    private String code;

    @ManyToOne(targetEntity = MerchantStoreEntity.class)
    @JoinColumn(name = "MerchantId", nullable = false)
    private MerchantStoreEntity store;

    @ManyToOne(targetEntity = ProductEntity.class)
    @JoinColumn(name = "ProductId", updatable = false, nullable = true)
    private ProductEntity product = null;

    @ManyToOne(targetEntity = ProductEntity.class)
    @JoinColumn(name = "RelatedProductId", updatable = false, nullable = true)
    private ProductEntity relatedProduct = null;

    @Column(name = "Active")
    private boolean active = true;
}
