package com.amazingenergy.saitamajpa.customer.entity.attribute;

import com.amazingenergy.saitamajpa.customer.entity.CustomerEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CustomerAttribute",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"OptionId", "CustomerId"})})
public class CustomerAttributeEntity {
    @Id
    @Column(name = "Id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OptionId", nullable = false)
    private CustomerOptionEntity option;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OptionValueId", nullable = false)
    private CustomerOptionValueEntity optionValue;

    @Column(name = "TextValue")
    private String textValue;

    @ManyToOne(targetEntity = CustomerEntity.class)
    @JoinColumn(name = "CustomerId", nullable = false)
    private CustomerEntity customer;
}
