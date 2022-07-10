package com.amazingenergy.saitamadomain.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address {
    private String city;
    private String postalCode;
    private String stateProvince;
    private String zone;//code
    private String country;//code
}
