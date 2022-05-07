package com.amazingenergy.saitamadomain.catalog.product.domain.image;

import com.amazingenergy.core.domain.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductImage extends Entity<UUID, ProductImage> {
    private InputStream image = null;
    private String productImage;
    private boolean defaultImage = true;
    private int imageType;
    private String productImageUrl;
    private boolean imageCrop;
    private List<ProductImageDescription> descriptions = new ArrayList<>();
    private int sortOrder = 0;

    public ProductImage() {
        super(UUID.randomUUID());
    }
}
