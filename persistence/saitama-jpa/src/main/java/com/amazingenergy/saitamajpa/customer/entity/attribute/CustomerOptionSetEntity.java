package com.amazingenergy.saitamajpa.customer.entity.attribute;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="CustomerOptionSet", uniqueConstraints={@UniqueConstraint(columnNames={"CustomerOptionId", "CustomerOptionValueId"})})
public class CustomerOptionSetEntity {
    @Id
    @Column(name = "Id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="CustomerOptionId", nullable=false)
    private CustomerOptionEntity option;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="CustomerOptionValueId", nullable=false)
    private CustomerOptionValueEntity optionValue;

    @Column(name="SortOrder")
    private Integer sortOrder;
}
