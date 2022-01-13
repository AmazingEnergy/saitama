package com.amazingenergy.saitamadomain.customer.domain;

import com.amazingenergy.saitamadomain.reference.domain.Country;
import com.amazingenergy.saitamadomain.reference.domain.Zone;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Billing implements Serializable {
    private String firstName;
    private String lastName;
    private String company;
    private String address;
    private String city;
    private String postalCode;
    private String telephone;
    private String state;
    private String longitude;
    private String latitude;
    private Country country;
    private Zone zone;
}
