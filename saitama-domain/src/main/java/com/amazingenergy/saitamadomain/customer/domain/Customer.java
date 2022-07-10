package com.amazingenergy.saitamadomain.customer.domain;

import com.amazingenergy.core.domain.AggregateRoot;
import com.amazingenergy.core.domain.AuditSection;
import com.amazingenergy.saitamadomain.common.Billing;
import com.amazingenergy.saitamadomain.common.Delivery;
import com.amazingenergy.saitamadomain.reference.domain.Language;
import com.amazingenergy.saitamadomain.customer.domain.attribute.CustomerAttribute;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import com.amazingenergy.saitamadomain.user.domain.Group;
import lombok.*;

import java.math.BigDecimal;
import java.util.*;

@Data
@EqualsAndHashCode(callSuper = true)
public class Customer extends AggregateRoot<UUID, Customer> {
    private String name;
    private String email;
    private Date dateOfBirth;
    private Getter getter;
    private String company;
    private Set<CustomerAttribute> attributes = new HashSet<>();
    private BigDecimal customerReviewAvg;
    private int customerReviewCount;
    private String provider;
    private Language defaultLanguage;
    private Delivery delivery = null;
    private Billing billing = null;
    private boolean anonymous;
    private List<Group> groups = new ArrayList<>();
    private AuditSection auditSection;
    private MerchantStore merchantStore;

    public Customer() {
        super(UUID.randomUUID());
    }
}
