package com.amazingenergy.saitamadomain.common;

import com.amazingenergy.saitamadomain.reference.domain.Country;
import com.amazingenergy.saitamadomain.reference.domain.Zone;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Delivery implements Serializable {
    private String firstName;
    private String lastName;
    private String company;
    private String address;
    private String city;
    private String postalCode;
    private String state;
    private String telephone;
    private Country country;
    private Zone zone;
}
