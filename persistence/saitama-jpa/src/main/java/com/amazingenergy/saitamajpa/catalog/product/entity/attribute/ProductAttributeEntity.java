package com.amazingenergy.saitamajpa.catalog.product.entity.attribute;

import com.amazingenergy.saitamajpa.catalog.product.entity.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ProductAttribute",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"OptionId", "OptionValueId", "ProductId"})})
public class ProductAttributeEntity {
    @Id
    @Column(name = "Id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "Price")
    private BigDecimal price;

    @Column(name = "SortOrder")
    private Integer sortOrder;

    @Column(name = "IsFree")
    private boolean isFree;

    @Column(name = "Weigh")
    private BigDecimal weight;

    @Column(name = "IsDefault")
    private boolean isDefault;

    @Column(name = "IsRequired")
    private boolean isRequired;

    @Column(name = "DisplayOnly")
    private boolean DisplayOnly;

    @Column(name = "Discounted")
    private boolean discounted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OptionId", nullable = false)
    private ProductOptionEntity option;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OptionValueId", nullable = false)
    private ProductOptionValueEntity optionValue;

    @ManyToOne(targetEntity = ProductEntity.class)
    @JoinColumn(name = "ProductId", nullable = false)
    private ProductEntity product;
}
