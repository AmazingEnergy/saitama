package com.amazingenergy.saitamajpa.customer;

import com.amazingenergy.saitamadomain.customer.domain.Customer;
import com.amazingenergy.saitamadomain.customer.domain.attribute.*;
import com.amazingenergy.saitamadomain.customer.domain.review.CustomerReview;
import com.amazingenergy.saitamajpa.customer.entity.CustomerEntity;
import com.amazingenergy.saitamajpa.customer.entity.attribute.*;
import com.amazingenergy.saitamajpa.customer.entity.review.CustomerReviewEntity;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {
    CustomerEntity to(Customer source);

    Customer from(CustomerEntity destination);

    CustomerReviewEntity to(CustomerReview source);

    CustomerReview from(CustomerReviewEntity destination);

    CustomerAttributeEntity to(CustomerAttribute source);

    CustomerAttribute from(CustomerAttributeEntity destination);

    CustomerOptionEntity to(CustomerOption source);

    CustomerOption from(CustomerOptionEntity destination);

    CustomerOptionDescriptionEntity to(CustomerOptionDescription source);

    CustomerOptionDescription from(CustomerOptionDescriptionEntity destination);

    CustomerOptionSetEntity to(CustomerOptionSet source);

    CustomerOptionSet from(CustomerOptionSetEntity destination);

    CustomerOptionValueEntity to(CustomerOptionValue source);

    CustomerOptionValue from(CustomerOptionValueEntity destination);

    CustomerOptionValueDescriptionEntity to(CustomerOptionValueDescription source);

    CustomerOptionValueDescription from(CustomerOptionValueDescriptionEntity destination);
}
