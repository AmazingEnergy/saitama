package com.amazingenergy.saitamajpa.tax.repository;

import com.amazingenergy.saitamajpa.tax.entity.TaxRateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaTaxRateRepository extends JpaRepository<TaxRateEntity, UUID> {
    @Query("select t from TaxRateEntity t " +
            "join fetch t.taxClass " +
            "join fetch t.merchantStore tm " +
            "join fetch t.country " +
            "left join fetch t.zone " +
            "left join fetch t.descriptions " +
            "left join t.parent " +
            "where tm.id=?1 " +
            "order by t.taxPriority asc")
    List<TaxRateEntity> findByStore(UUID storeId);

    @Query("select t from TaxRateEntity t " +
            "join fetch t.taxClass " +
            "join fetch t.merchantStore tm " +
            "join fetch t.country " +
            "left join fetch t.zone " +
            "left join fetch t.descriptions td " +
            "left join t.parent " +
            "where tm.id=?1 and td.language.id=?2 " +
            "order by t.taxPriority asc")
    List<TaxRateEntity> findByStoreAndLanguage(UUID storeId, UUID languageId);

    @Query("select t from TaxRateEntity t " +
            "join fetch t.taxClass " +
            "join fetch t.merchantStore tm " +
            "join fetch t.country " +
            "left join fetch t.zone " +
            "left join fetch t.descriptions td " +
            "left join t.parent " +
            "where tm.id=?2 and t.code=?1")
    Optional<TaxRateEntity> findByStoreAndCode(String code, UUID storeId);

    @Query("select t from TaxRateEntity t " +
            "join fetch t.taxClass " +
            "join fetch t.merchantStore tm " +
            "join fetch t.country " +
            "left join fetch t.zone " +
            "left join fetch t.descriptions td " +
            "left join t.parent " +
            "where tm.id=?2 and t.id=?1")
    Optional<TaxRateEntity> findByStoreAndId(UUID id, UUID storeId);

    @Query("select t from TaxRateEntity t " +
            "join fetch t.taxClass " +
            "join fetch t.merchantStore tm " +
            "join fetch t.country " +
            "left join fetch t.zone " +
            "left join fetch t.descriptions td " +
            "left join t.parent " +
            "where t.id=?1")
    Optional<TaxRateEntity> findOne(UUID id);

    @Query("select t from TaxRateEntity t " +
            "join fetch t.taxClass " +
            "join fetch t.merchantStore tm " +
            "join fetch t.country tc " +
            "left join fetch t.zone tz " +
            "left join fetch t.descriptions td " +
            "left join t.parent " +
            "where tm.id=?1 AND (tz.id=?2 OR tz IS NULL) and tc.id=?3 and td.language.id=?4 " +
            "order by t.taxPriority asc")
    List<TaxRateEntity> findByMerchantAndZoneAndCountryAndLanguage(UUID storeId, UUID zoneId, UUID countryId, UUID languageId);

    @Query("select t from TaxRateEntity t " +
            "join fetch t.taxClass " +
            "join fetch t.merchantStore tm " +
            "join fetch t.country tc " +
            "left join fetch t.zone tz " +
            "left join fetch t.descriptions td " +
            "left join t.parent " +
            "where tm.id=?1 AND t.stateProvince=?2 and tc.id=?3 and td.language.id=?4 " +
            "order by t.taxPriority asc")
    List<TaxRateEntity> findByMerchantAndProvinceAndCountryAndLanguage(UUID storeId, String province, UUID countryId, UUID languageId);
}
