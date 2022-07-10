package com.amazingenergy.saitamajpa.catalog.product.entity;

import com.amazingenergy.saitamajpa.common.DescriptionEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ProductDescription",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"ProductId", "LanguageId"})},
        indexes = {@Index(name = "PRODUCT_DESCRIPTION_SEF_URL", columnList = "SefUrl")})
public class ProductDescriptionEntity extends DescriptionEntity {
    @ManyToOne(targetEntity = ProductEntity.class)
    @JoinColumn(name = "ProductId", nullable = false)
    private ProductEntity product;

    @Column(name = "ProductHighlight")
    private String productHighlight;

    @Column(name = "DownloadLink")
    private String productExternalDl;

    @Column(name = "SefUrl")
    private String seUrl;

    @Column(name = "MetaTitle")
    private String metaTagTitle;

    @Column(name = "MetaKeywords")
    private String metaTagKeywords;

    @Column(name = "MetaDescription")
    private String metaTagDescription;
}
