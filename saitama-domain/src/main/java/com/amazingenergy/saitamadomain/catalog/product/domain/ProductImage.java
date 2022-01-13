package com.amazingenergy.saitamadomain.catalog.product.domain;

import com.amazingenergy.core.domain.Entity;
import com.amazingenergy.saitamadomain.reference.domain.Description;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductImage extends Entity<UUID, ProductImage> {
    private String productImage;
    private boolean defaultImage = true;
    private int imageType;
    private String productImageUrl;
    private boolean imageCrop;
    private List<Description> descriptions = new ArrayList<>();
    private int sortOrder = 0;

    public ProductImage() {
        super(UUID.randomUUID());
    }
}
