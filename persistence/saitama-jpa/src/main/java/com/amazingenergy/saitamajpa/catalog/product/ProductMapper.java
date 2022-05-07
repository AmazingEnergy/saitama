package com.amazingenergy.saitamajpa.catalog.product;

import com.amazingenergy.saitamadomain.catalog.product.domain.Product;
import com.amazingenergy.saitamadomain.catalog.product.domain.ProductDescription;
import com.amazingenergy.saitamadomain.catalog.product.domain.review.ProductReview;
import com.amazingenergy.saitamadomain.catalog.product.domain.type.ProductType;
import com.amazingenergy.saitamadomain.catalog.product.domain.attribute.*;
import com.amazingenergy.saitamadomain.catalog.product.domain.availability.ProductAvailability;
import com.amazingenergy.saitamadomain.catalog.product.domain.availability.ProductDimensions;
import com.amazingenergy.saitamadomain.catalog.product.domain.availability.ProductVariation;
import com.amazingenergy.saitamadomain.catalog.product.domain.availability.ProductVariationImage;
import com.amazingenergy.saitamadomain.catalog.product.domain.image.ProductImage;
import com.amazingenergy.saitamadomain.catalog.product.domain.image.ProductImageDescription;
import com.amazingenergy.saitamadomain.catalog.product.domain.manufacturer.Manufacturer;
import com.amazingenergy.saitamadomain.catalog.product.domain.manufacturer.ManufacturerDescription;
import com.amazingenergy.saitamadomain.catalog.product.domain.price.ProductPrice;
import com.amazingenergy.saitamadomain.catalog.product.domain.price.ProductPriceDescription;
import com.amazingenergy.saitamadomain.catalog.product.domain.relationship.ProductRelationship;
import com.amazingenergy.saitamadomain.catalog.product.domain.review.ProductReviewDescription;
import com.amazingenergy.saitamadomain.catalog.product.domain.type.ProductTypeDescription;
import com.amazingenergy.saitamadomain.common.Description;
import com.amazingenergy.saitamajpa.catalog.product.entity.ProductDescriptionEntity;
import com.amazingenergy.saitamajpa.catalog.product.entity.ProductEntity;
import com.amazingenergy.saitamajpa.catalog.product.entity.attribute.*;
import com.amazingenergy.saitamajpa.catalog.product.entity.availability.EmbeddableProductDimensions;
import com.amazingenergy.saitamajpa.catalog.product.entity.availability.ProductAvailabilityEntity;
import com.amazingenergy.saitamajpa.catalog.product.entity.availability.ProductVariationEntity;
import com.amazingenergy.saitamajpa.catalog.product.entity.availability.ProductVariationImageEntity;
import com.amazingenergy.saitamajpa.catalog.product.entity.image.ProductImageDescriptionEntity;
import com.amazingenergy.saitamajpa.catalog.product.entity.image.ProductImageEntity;
import com.amazingenergy.saitamajpa.catalog.product.entity.manufacturer.ManufacturerDescriptionEntity;
import com.amazingenergy.saitamajpa.catalog.product.entity.manufacturer.ManufacturerEntity;
import com.amazingenergy.saitamajpa.catalog.product.entity.price.ProductPriceDescriptionEntity;
import com.amazingenergy.saitamajpa.catalog.product.entity.price.ProductPriceEntity;
import com.amazingenergy.saitamajpa.catalog.product.entity.relationship.ProductRelationshipEntity;
import com.amazingenergy.saitamajpa.catalog.product.entity.review.ProductReviewDescriptionEntity;
import com.amazingenergy.saitamajpa.catalog.product.entity.review.ProductReviewEntity;
import com.amazingenergy.saitamajpa.catalog.product.entity.type.ProductTypeDescriptionEntity;
import com.amazingenergy.saitamajpa.catalog.product.entity.type.ProductTypeEntity;
import org.mapstruct.Mapper;

@Mapper
public interface ProductMapper {
    ProductEntity to(Product source);

    Product from(ProductEntity destination);

    ProductDescriptionEntity to(ProductDescription source);

    ProductDescription from(ProductDescriptionEntity destination);

    ProductAttributeEntity to(ProductAttribute source);

    ProductAttribute from(ProductAttributeEntity destination);

    ProductOptionEntity to(ProductOption source);

    ProductOption from(ProductOptionEntity destination);

    ProductOptionDescriptionEntity to(ProductOptionDescription source);

    ProductOptionDescription from(ProductOptionDescriptionEntity destination);

    ProductOptionSetEntity to(ProductOptionSet source);

    ProductOptionSet from(ProductOptionSetEntity destination);

    ProductOptionValueEntity to(ProductOptionValue source);

    ProductOptionValue from(ProductOptionValueEntity destination);

    ProductOptionValueDescriptionEntity to(ProductOptionValueDescription source);

    ProductOptionValueDescription from(ProductOptionValueDescriptionEntity destination);

    EmbeddableProductDimensions to(ProductDimensions source);

    ProductDimensions from(EmbeddableProductDimensions destination);

    ProductAvailabilityEntity to(ProductAvailability source);

    ProductAvailability from(ProductAvailabilityEntity destination);

    ProductVariationEntity to(ProductVariation source);

    ProductVariation from(ProductVariationEntity destination);

    ProductVariationImageEntity to(ProductVariationImage source);

    ProductVariationImage from(ProductVariationImageEntity destination);

    ProductImageEntity to(ProductImage source);

    ProductImage from(ProductImageEntity destination);

    ProductImageDescriptionEntity to(ProductImageDescription source);

    ProductImageDescription from(ProductImageDescriptionEntity destination);

    ManufacturerEntity to(Manufacturer source);

    Manufacturer from(ManufacturerEntity destination);

    ManufacturerDescriptionEntity to(ManufacturerDescription source);

    ManufacturerDescription from(ManufacturerDescriptionEntity destination);

    ProductPriceEntity to(ProductPrice source);

    ProductPrice from(ProductPriceEntity destination);

    ProductPriceDescriptionEntity to(ProductPriceDescription source);

    ProductPriceDescription from(ProductPriceDescriptionEntity destination);

    ProductRelationshipEntity to(ProductRelationship source);

    ProductRelationship from(ProductRelationshipEntity destination);

    ProductReviewEntity to(ProductReview source);

    ProductReview from(ProductReviewEntity destination);

    ProductReviewDescriptionEntity to(ProductReviewDescription source);

    ProductReviewDescription from(ProductReviewDescriptionEntity destination);

    ProductTypeEntity to(ProductType source);

    ProductType from(ProductTypeEntity destination);

    ProductTypeDescriptionEntity to(ProductTypeDescription source);

    ProductTypeDescription from(ProductTypeDescriptionEntity destination);
}
