package com.amazingenergy.saitamadomain.catalog.product.repository;

import com.amazingenergy.core.repository.EntityRepository;
import com.amazingenergy.saitamadomain.catalog.product.domain.Product;
import com.amazingenergy.saitamadomain.catalog.product.domain.image.ProductImage;
import com.amazingenergy.saitamadomain.catalog.product.domain.image.ProductImageSize;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import com.amazingenergy.saitamadomain.common.content.OutputContentFile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductImageRepository extends EntityRepository<UUID, ProductImage> {
    /**
     * Get a product image by name for a given product id
     */
    Optional<ProductImage> getProductImage(UUID imageId, UUID productId, MerchantStore store);

    void removeProductImage(ProductImage productImage);

    void addProductImages(List<ProductImage> productImages);
}
