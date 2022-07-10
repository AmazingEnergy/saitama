package com.amazingenergy.saitamajpa.common;

import com.amazingenergy.corejpa.audit.AuditListener;
import com.amazingenergy.corejpa.audit.Auditable;
import com.amazingenergy.corejpa.audit.EmbeddableAuditSection;
import com.amazingenergy.saitamajpa.catalog.category.entity.CategoryEntity;
import com.amazingenergy.saitamajpa.reference.entity.CountryEntity;
import com.amazingenergy.saitamajpa.reference.entity.GeoZoneEntity;
import com.amazingenergy.saitamajpa.reference.entity.LanguageEntity;
import com.amazingenergy.saitamajpa.reference.entity.ZoneEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(value = AuditListener.class)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class DescriptionEntity implements Auditable {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Embedded
    private EmbeddableAuditSection auditSection;

    @ManyToOne(optional = false)
    @JoinColumn(name = "LanguageId")
    private LanguageEntity language;

    @Column(name = "Name", nullable = false, length = 120)
    private String name;

    @Column(name = "Title", length = 100)
    private String title;

    @Column(name = "Description")
    @Type(type = "org.hibernate.type.TextType")
    private String description;
}
