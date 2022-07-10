package com.amazingenergy.saitamajpa.reference.repository;

import com.amazingenergy.saitamajpa.reference.entity.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaCountryRepository extends JpaRepository<CountryEntity, UUID> {

    @Query("select c from CountryEntity c " +
            "left join fetch c.descriptions cd " +
            "where c.isoCode=?1")
    Optional<CountryEntity> findByIsoCode(String code);

    @Query("select c from CountryEntity c " +
            "left join fetch c.descriptions cd " +
            "where cd.language.id=?2 and c.isoCode in ?1")
    List<CountryEntity> listCountries(List<String> isoCodes, UUID languageId);

    @Query("select c from CountryEntity c " +
            "left join fetch c.descriptions cd " +
            "where cd.language.id=?1")
    List<CountryEntity> listByLanguage(UUID languageId);

    /** get country including zones by language **/
    @Query("select distinct c from CountryEntity c " +
            "left join fetch c.descriptions cd " +
            "left join fetch c.zones cz " +
            "left join fetch cz.descriptions " +
            "where cd.language.id=?1")
    List<CountryEntity> listCountryZonesByLanguage(UUID languageId);
}
