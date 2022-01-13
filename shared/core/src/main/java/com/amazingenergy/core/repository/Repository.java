package com.amazingenergy.core.repository;

import com.amazingenergy.core.domain.AggregateRoot;

public interface Repository<T extends AggregateRoot> {
    T save(T entry);
}
