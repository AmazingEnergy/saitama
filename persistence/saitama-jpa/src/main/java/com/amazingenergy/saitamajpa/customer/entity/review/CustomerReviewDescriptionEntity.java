package com.amazingenergy.saitamajpa.customer.entity.review;

import com.amazingenergy.saitamajpa.common.DescriptionEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CustomerReviewDescription",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"CustomerReviewId", "LanguageId"})})
public class CustomerReviewDescriptionEntity extends DescriptionEntity {
    @ManyToOne(targetEntity = CustomerReviewEntity.class)
    @JoinColumn(name = "CustomerReviewId")
    private CustomerReviewEntity customerReview;
}
