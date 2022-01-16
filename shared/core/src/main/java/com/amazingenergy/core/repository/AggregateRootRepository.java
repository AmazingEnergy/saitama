package com.amazingenergy.core.repository;

import com.amazingenergy.core.domain.AggregateRoot;

import java.io.Serializable;

public interface AggregateRootRepository<K extends Serializable & Comparable<K>, E extends AggregateRoot<K, ?>> extends Repository<K, E> {
}
