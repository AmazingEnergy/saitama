package com.amazingenergy.saitamajpa.catalog.product.repository;

import com.amazingenergy.corejpa.repository.BaseRepositoryImpl;
import com.amazingenergy.saitamadomain.catalog.product.domain.image.ProductImage;
import com.amazingenergy.saitamadomain.catalog.product.repository.ProductImageRepository;
import com.amazingenergy.saitamadomain.common.content.FileContentType;
import com.amazingenergy.saitamadomain.common.content.ImageContentFile;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import com.amazingenergy.saitamajpa.catalog.product.ProductMapper;
import com.amazingenergy.saitamajpa.catalog.product.entity.image.ProductImageEntity;
import org.springframework.stereotype.Repository;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ProductImageRepositoryImpl extends BaseRepositoryImpl<UUID, ProductImage, ProductImageEntity> implements ProductImageRepository {

    private final JpaProductImageRepository jpaRepository;
    private final ProductMapper mapper;

    public ProductImageRepositoryImpl(JpaProductImageRepository jpaRepository, ProductMapper mapper) {
        super(jpaRepository);
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public void addProductImages(List<ProductImage> productImages) {
        for (var productImage : productImages) {
            InputStream inputStream = productImage.getImage();
            var cmsContentImage = new ImageContentFile();
            cmsContentImage.setFileName( productImage.getProductImage() );
            cmsContentImage.setFile( inputStream );
            cmsContentImage.setFileContentType(FileContentType.PRODUCT);
            // TODO: Implement ProductFileManager upload image
            this.save(productImage);
        }
    }

    @Override
    public void removeProductImage(ProductImage productImage) {
        // TODO: Implement ProductFileManager remove image
        this.delete(productImage);
    }

    @Override
    public Optional<ProductImage> getProductImage(UUID imageId, UUID productId, MerchantStore store) {
        return jpaRepository.finById(imageId, productId, store.getCode()).map(this::from);
    }

    @Override
    public ProductImageEntity to(ProductImage source) {
        return mapper.to(source);
    }

    @Override
    public ProductImage from(ProductImageEntity destination) {
        return mapper.from(destination);
    }
}
