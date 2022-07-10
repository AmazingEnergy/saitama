package com.amazingenergy.saitamajpa.reference.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Currency")
@Cacheable
public class CurrencyEntity {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "CurrencyCurrencyCode" ,nullable = false, unique = true)
    private java.util.Currency currency;

    @Column(name = "Supported")
    private Boolean supported = true;

    @Column(name = "Code", unique = true)
    private String code;

    @Column(name = "Name", unique = true)
    private String name;
}
