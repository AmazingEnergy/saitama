package com.amazingenergy.saitamajpa.customer.entity.attribute;

import com.amazingenergy.saitamajpa.merchant.entity.MerchantStoreEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CustomerOptionValue",
        indexes = {@Index(name = "CUST_OPT_VAL_CODE_IDX", columnList = "Code")},
        uniqueConstraints = @UniqueConstraint(columnNames = {"MerchantId", "Code"}))
public class CustomerOptionValueEntity {
    @Id
    @Column(name = "Id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "SortOrder")
    private Integer sortOrder;

    @Column(name = "Image")
    private String Image;

    @Column(name = "Code")
    private String code;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "customerOptionValue")
    private Set<CustomerOptionValueDescriptionEntity> descriptions = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MerchantId", nullable = false)
    private MerchantStoreEntity merchantStore;
}
