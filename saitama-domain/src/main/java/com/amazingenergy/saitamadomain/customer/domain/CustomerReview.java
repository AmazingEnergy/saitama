package com.amazingenergy.saitamadomain.customer.domain;

import com.amazingenergy.core.domain.AuditSection;
import com.amazingenergy.core.domain.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class CustomerReview extends Entity<UUID, CustomerReview> {
    private double reviewRating;
    private long reviewRead;
    private Date reviewDate;
    private int status;
    private AuditSection auditSection;
    private Customer customer;
    private Customer reviewedCustomer;

    public CustomerReview() {
        super(UUID.randomUUID());
    }
}
