package com.amazingenergy.saitamajpa.catalog.product.entity.image;

import com.amazingenergy.saitamajpa.catalog.product.entity.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ProductImage")
public class ProductImageEntity {
    @Id
    @Column(name = "Id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "productImage", cascade = CascadeType.ALL)
    private List<ProductImageDescriptionEntity> descriptions = new ArrayList<>();

    @Column(name = "ProductImage")
    private String productImage;

    @Column(name = "DefaultImage")
    private boolean defaultImage = true;

    @Column(name = "ImageType")
    private int imageType;

    @Column(name = "ImageUrl")
    private String productImageUrl;

    @Column(name = "ImageCrop")
    private boolean imageCrop;

    @ManyToOne(targetEntity = ProductEntity.class)
    @JoinColumn(name = "ProductId", nullable = false)
    private ProductEntity product;

    @Column(name = "SortOrder")
    private Integer sortOrder = 0;
}
