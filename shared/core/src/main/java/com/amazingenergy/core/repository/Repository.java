package com.amazingenergy.core.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface Repository<K extends Serializable & Comparable<K>, E> {
    E save(E entity);
    List<E> saveAll(Iterable<E> entities);
    void update(E entity);
    void create(E entity);
    void delete(E entity);
    Optional<E> findById(K id);
    List<E> findAll();
    Long count();
    void flush();
}
