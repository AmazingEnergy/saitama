package com.amazingenergy.saitamajpa.catalog.product.entity.availability;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class EmbeddableProductDimensions {
    @Column(name = "Length")
    private BigDecimal length;

    @Column(name = "Width")
    private BigDecimal width;

    @Column(name = "Height")
    private BigDecimal height;

    @Column(name = "Weight")
    private BigDecimal weight;
}
