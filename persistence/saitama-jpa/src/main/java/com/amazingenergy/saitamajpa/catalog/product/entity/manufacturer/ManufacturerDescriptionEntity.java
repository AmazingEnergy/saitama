package com.amazingenergy.saitamajpa.catalog.product.entity.manufacturer;

import com.amazingenergy.saitamajpa.common.DescriptionEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ManufacturerDescription",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"ManufacturerId", "LanguageId"})})
public class ManufacturerDescriptionEntity extends DescriptionEntity {
    @ManyToOne(targetEntity = ManufacturerEntity.class)
    @JoinColumn(name = "ManufacturerId", nullable = false)
    private ManufacturerEntity manufacturer;

    @Column(name = "Url")
    private String url;

    @Column(name = "UrlClicked")
    private Integer urlClicked;

    @Column(name = "DateLastClick")
    private Date dateLastClick;
}
