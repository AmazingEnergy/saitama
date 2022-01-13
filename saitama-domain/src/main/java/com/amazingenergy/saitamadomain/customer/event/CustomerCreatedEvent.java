package com.amazingenergy.saitamadomain.customer.event;

import com.amazingenergy.core.domain.Event;
import com.amazingenergy.saitamadomain.customer.domain.Customer;

public class CustomerCreatedEvent extends Event<Customer> {
    public CustomerCreatedEvent(Customer customer) {
        super(customer);
    }
}
