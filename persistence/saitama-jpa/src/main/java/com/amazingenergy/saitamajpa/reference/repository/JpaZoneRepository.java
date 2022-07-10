package com.amazingenergy.saitamajpa.reference.repository;

import com.amazingenergy.saitamajpa.reference.entity.ZoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaZoneRepository extends JpaRepository<ZoneEntity, UUID> {
    Optional<ZoneEntity> findByCode(String code);

    @Query("select z from ZoneEntity z " +
            "left join fetch z.descriptions zd " +
            "where zd.language.id=?1")
    List<ZoneEntity> listByLanguage(UUID id);

    @Query("select z from ZoneEntity z " +
            "left join fetch z.descriptions zd " +
            "join fetch z.country zc " +
            "where zc.isoCode=?1 and zd.language.id=?2")
    List<ZoneEntity> listByLanguageAndCountry(String isoCode, UUID languageId);
}
