package com.amazingenergy.saitamaappservice.customer.model;

import com.amazingenergy.core.Criteria;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerCriteria extends Criteria {
    private String firstName;
    private String lastName;
    private String name;
    private String email;
    private String country;
}
