package com.amazingenergy.saitamajpa.reference.entity;

import com.amazingenergy.saitamajpa.catalog.category.entity.CategoryEntity;
import com.amazingenergy.saitamajpa.core.AuditListener;
import com.amazingenergy.saitamajpa.core.Auditable;
import com.amazingenergy.saitamajpa.core.EmbeddableAuditSection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Description")
@EntityListeners(value = AuditListener.class)
public class DescriptionEntity implements Auditable {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "DescriptionGen")
    private UUID id;

    @Embedded
    private EmbeddableAuditSection auditSection;

    @ManyToOne(optional = false)
    @JoinColumn(name = "LanguageId")
    private LanguageEntity language;

    @Column(name="Name", nullable = false, length=120)
    private String name;

    @Column(name="Title", length=100)
    private String title;

    @Column(name="Description")
    @Type(type = "org.hibernate.type.TextType")
    private String description;

    @ManyToOne(targetEntity = CountryEntity.class)
    @JoinColumn(name = "CountryId", nullable = false)
    private CountryEntity country;

    @ManyToOne(targetEntity = ZoneEntity.class)
    @JoinColumn(name = "ZoneId", nullable = false)
    private ZoneEntity zone;

    @ManyToOne(targetEntity = GeoZoneEntity.class)
    @JoinColumn(name = "GeoZoneId")
    private GeoZoneEntity geoZone;

    @ManyToOne(targetEntity = CategoryEntity.class)
    @JoinColumn(name = "CategoryId", nullable = false)
    private CategoryEntity category;
}
