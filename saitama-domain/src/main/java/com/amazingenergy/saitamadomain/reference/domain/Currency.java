package com.amazingenergy.saitamadomain.reference.domain;

import com.amazingenergy.core.domain.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class Currency extends Entity<UUID, Currency> {
    private String code;
    private String name;
    private boolean supported = true;
    private java.util.Currency currency;

    private Currency() {
        super(UUID.randomUUID());
    }

    public Currency(UUID id, String code, String name, boolean supported, java.util.Currency currency) {
        super(id);
        this.code = code;
        this.name = name;
        this.supported = supported;
        this.currency = currency;
    }

    public void setCurrency(java.util.Currency currency) {
        this.currency = currency;
        this.code = currency.getCurrencyCode();
    }

    public String getCode() {
        if (currency.getCurrencyCode() != code) {
            return currency.getCurrencyCode();
        }
        return code;
    }

    public String getSymbol() {
        return currency.getSymbol();
    }
}
