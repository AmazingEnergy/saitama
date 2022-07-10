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
public class EmbeddableBilling {
    @Column(name = "BillingFirstName", length = 64, nullable = false)
    private String lastName;

    @Column(name = "BillingLastName", length = 64, nullable = false)
    private String firstName;

    @Column(name = "BillingCompany", length = 100)
    private String company;

    @Column(name = "BillingAddress", length = 256)
    private String address;

    @Column(name = "BillingCity", length = 100)
    private String city;

    @Column(name = "BillingPostalCode", length = 20)
    private String postalCode;

    @Column(name = "BillingTelephone", length = 32)
    private String telephone;

    @Column(name = "BillingState", length = 100)
    private String state;

    @Column(name = "BillingLongitude", length = 100)
    private String longitude;

    @Column(name = "BillingLatitude", length = 100)
    private String latitude;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = CountryEntity.class)
    @JoinColumn(name = "BillingCountryId", nullable = false)
    private CountryEntity country;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = ZoneEntity.class)
    @JoinColumn(name = "BillingZoneId", nullable = true)
    private ZoneEntity zone;
}
