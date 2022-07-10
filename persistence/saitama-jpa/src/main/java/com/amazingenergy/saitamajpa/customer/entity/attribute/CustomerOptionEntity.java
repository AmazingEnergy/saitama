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
@Table(name = "CustomerOption",
        indexes = {@Index(name = "CUST_OPT_CODE_IDX", columnList = "Code")},
        uniqueConstraints = @UniqueConstraint(columnNames = {"MerchantId", "Code"}))
public class CustomerOptionEntity {
    @Id
    @Column(name = "Id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "SortOrder")
    private Integer sortOrder;

    @Column(name = "Type", length = 10)
    private String type;

    @Column(name = "Code")
    private String code;

    @Column(name = "Active")
    private boolean active;

    @Column(name = "PublicOption")
    private boolean publicOption;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "customerOption")
    private Set<CustomerOptionDescriptionEntity> descriptions = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MerchantId", nullable = false)
    private MerchantStoreEntity merchantStore;
}
