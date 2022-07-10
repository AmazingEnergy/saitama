package com.amazingenergy.saitamadomain.reference.repository;

import com.amazingenergy.core.repository.EntityRepository;
import com.amazingenergy.saitamadomain.reference.domain.Currency;

import java.util.Optional;
import java.util.UUID;

public interface CurrencyRepository extends EntityRepository<UUID, Currency> {
    Optional<Currency> getByCode(String code);
}
