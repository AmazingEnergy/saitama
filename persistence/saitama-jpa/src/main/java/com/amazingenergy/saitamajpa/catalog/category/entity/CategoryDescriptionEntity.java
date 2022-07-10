package com.amazingenergy.saitamajpa.catalog.category.entity;

import com.amazingenergy.saitamajpa.common.DescriptionEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CategoryDescription", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "CategoryId",
                "LanguageId"
        })
})
public class CategoryDescriptionEntity extends DescriptionEntity {
    @ManyToOne(targetEntity = CategoryEntity.class)
    @JoinColumn(name = "CategoryId", nullable = false)
    private CategoryEntity category;

    @Column(name = "SefUrl", length = 120)
    private String seUrl;

    @Column(name = "Highlight")
    private String categoryHighlight;

    @Column(name = "MetaTitle", length = 120)
    private String metaTagTitle;

    @Column(name = "MetaKeywords")
    private String metaTagKeywords;

    @Column(name = "MetaDescription")
    private String metaTagDescription;
}
