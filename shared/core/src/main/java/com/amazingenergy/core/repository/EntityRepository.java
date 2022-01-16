package com.amazingenergy.core.repository;

import com.amazingenergy.core.domain.Entity;

import java.io.Serializable;

public interface EntityRepository<K extends Serializable & Comparable<K>, E extends Entity<K, ?>> extends Repository<K, E> {
}
