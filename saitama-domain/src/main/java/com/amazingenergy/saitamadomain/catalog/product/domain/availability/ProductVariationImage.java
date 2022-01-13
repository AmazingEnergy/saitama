package com.amazingenergy.saitamadomain.catalog.product.domain.availability;

import com.amazingenergy.core.domain.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductVariationImage extends Entity<UUID, ProductVariationImage> {
    private String image;
    private boolean defaultImage;

    public ProductVariationImage() {
        super(UUID.randomUUID());
    }
}
