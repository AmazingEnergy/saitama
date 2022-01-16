package com.amazingenergy.corejpa.repository;

import com.amazingenergy.core.mapper.MappingSupport;
import com.amazingenergy.core.repository.Repository;
import com.google.common.collect.Streams;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class BaseRepositoryImpl<K extends Serializable & Comparable<K>, E, PE> implements Repository<K, E>, MappingSupport<E, PE> {

    private Class<E> objectClass;

    private final JpaRepository<PE, K> jpaRepository;

    @SuppressWarnings("unchecked")
    public BaseRepositoryImpl(JpaRepository<PE, K> jpaRepository) {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.objectClass = (Class<E>) genericSuperclass.getActualTypeArguments()[1];
        this.jpaRepository = jpaRepository;
    }

    protected final Class<E> getObjectClass() {
        return objectClass;
    }

    @Override
    public E save(E entity) {
        var savedEntity = jpaRepository.saveAndFlush(to(entity));
        return from(savedEntity);
    }

    @Override
    public Iterable<E> saveAll(Iterable<E> entities) {
        var mappedEntities = Streams.stream(entities).map(this::to).collect(Collectors.toList());
        var savedEntities = jpaRepository.saveAll(mappedEntities);
        return savedEntities.stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public void update(E entity) {
        save(entity);
    }

    @Override
    public void create(E entity) {
        save(entity);
    }

    @Override
    public void delete(E entity) {
        jpaRepository.delete(to(entity));
    }

    @Override
    public Optional<E> findById(K id) {
        return jpaRepository.findById(id).map(this::from);
    }

    @Override
    public Iterable<E> findAll() {
        return jpaRepository.findAll().stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public Long count() {
        return jpaRepository.count();
    }

    @Override
    public void flush() {
        jpaRepository.flush();
    }
}
