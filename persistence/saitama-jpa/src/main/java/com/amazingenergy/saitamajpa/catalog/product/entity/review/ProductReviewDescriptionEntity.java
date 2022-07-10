package com.amazingenergy.saitamajpa.catalog.product.entity.review;

import com.amazingenergy.saitamajpa.common.DescriptionEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ProductReviewDescription",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"ProductReviewId", "languageId"})})
public class ProductReviewDescriptionEntity extends DescriptionEntity {
    @ManyToOne(targetEntity = ProductReviewEntity.class)
    @JoinColumn(name = "ProductReviewId")
    private ProductReviewEntity productReview;
}
