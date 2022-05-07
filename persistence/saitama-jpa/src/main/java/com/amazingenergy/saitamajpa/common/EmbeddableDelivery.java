package com.amazingenergy.saitamajpa.common;

import com.amazingenergy.saitamajpa.reference.entity.CountryEntity;
import com.amazingenergy.saitamajpa.reference.entity.ZoneEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class EmbeddableDelivery {
    @Column(name = "DeliveryLastName", length = 64)
    private String lastName;

    @Column(name = "DeliveryFirstName", length = 64)
    private String firstName;

    @Column(name = "DeliveryCompany", length = 100)
    private String company;

    @Column(name = "DeliveryAddress", length = 256)
    private String address;

    @Column(name = "DeliveryCity", length = 100)
    private String city;

    @Column(name = "DeliveryPostalCode", length = 20)
    private String postalCode;

    @Column(name = "DeliveryState", length = 100)
    private String state;

    @Column(name = "DeliveryTelephone", length = 32)
    private String telephone;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = CountryEntity.class)
    @JoinColumn(name = "DeliveryCountryId", nullable = true)
    private CountryEntity country;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = ZoneEntity.class)
    @JoinColumn(name = "DeliveryZoneId", nullable = true)
    private ZoneEntity zone;
}
