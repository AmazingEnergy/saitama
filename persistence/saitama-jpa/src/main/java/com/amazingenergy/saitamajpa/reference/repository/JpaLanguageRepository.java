package com.amazingenergy.saitamajpa.reference.repository;

import com.amazingenergy.saitamajpa.reference.entity.LanguageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaLanguageRepository extends JpaRepository<LanguageEntity, UUID> {
    Optional<LanguageEntity> findByCode(String code);
}
