package com.amazingenergy.saitamajpa.merchant.repository;

import com.amazingenergy.saitamajpa.merchant.entity.MerchantStoreEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaMerchantStoreRepository extends JpaRepository<MerchantStoreEntity, UUID> {

    @Query(value = "select distinct m from MerchantStoreEntity m " +
            "left join fetch m.parent mp " +
            "left join fetch m.country mc " +
            "left join fetch m.currency mcr " +
            "left join fetch m.zone mz " +
            "left join fetch m.defaultLanguage md " +
            "left join fetch m.languages mls " +
            "where mp.code = ?1",
            countQuery = "select count(distinct m) from MerchantStoreEntity m " +
                    "join m.parent mp " +
                    "where mp.code = ?1")
    Page<MerchantStoreEntity> listByStore(String code, Pageable pageable);


    @Query(value = "select distinct m from MerchantStoreEntity m " +
            "left join fetch m.parent mp " +
            "left join fetch m.country mc " +
            "left join fetch m.currency mcr " +
            "left join fetch m.zone mz " +
            "left join fetch m.defaultLanguage md " +
            "left join fetch m.languages mls " +
            "where (?1 is null or m.storename like %?1%)",
            countQuery = "select count(distinct m) from MerchantStoreEntity m " +
                    "where (?1 is null or m.storename like %?1%)")
    Page<MerchantStoreEntity> listAll(String storeName, Pageable pageable);

    @Query(value = "select distinct m from MerchantStoreEntity m " +
            "left join fetch m.parent mp " +
            "left join fetch m.country mc " +
            "left join fetch m.currency mcr " +
            "left join fetch m.zone mz " +
            "left join fetch m.defaultLanguage md " +
            "left join fetch m.languages mls " +
            "where m.retailer = true and (?1 is null or m.storename like %?1%)",
            countQuery = "select count(distinct m) from MerchantStoreEntity m " +
                    "join m.parent " +
                    "where m.retailer = true and (?1 is null or m.storename like %?1%)")
    Page<MerchantStoreEntity> listAllRetailers(String storeName, Pageable pageable);


    @Query(value = "select distinct m from MerchantStoreEntity m left join m.parent mp "
            + "left join fetch m.country pc "
            + "left join fetch m.currency pcu "
            + "left join fetch m.languages pl "
            + "left join fetch m.zone pz "
            + "where mp.code = ?1 or m.code = ?1 "
            + "and (?2 is null or (m.storename like %?2% or mp.storename like %?2%))",
            countQuery = "select count(distinct m) from MerchantStoreEntity m " +
                    "left join m.parent mp " +
                    "where mp.code = ?1 or m.code = ?1 " +
                    "and (?2 is null or (m.storename like %?2% or mp.storename like %?2%))")
    Page<MerchantStoreEntity> listChildren(String storeCode, String storeName, Pageable pageable);

    @Query(value = "select * from MerchantStore m " +
            "where (m.Code = ?1 or (?2 is null or m.ParentId = ?2)) " +
            "and (?3 is null or m.Name like %?3%)",
            countQuery = "select count(*) from MerchantStore m " +
                    "where (m.Code = ?1 or (?2 is null or m.ParentId = ?2)) " +
                    "and (?3 is null or m.Name like %?3%)",
            nativeQuery = true)
    Page<MerchantStoreEntity> listByGroup(String storeCode, UUID id, String storeName, Pageable pageable);

    @Query("select m from MerchantStoreEntity m "
            + "left join fetch m.parent mp"
            + "left join fetch m.country mc "
            + "left join fetch m.currency mcr "
            + "left join fetch m.zone mz "
            + "left join fetch m.defaultLanguage md "
            + "left join fetch m.languages mls " +
            "where m.code = ?1")
    Optional<MerchantStoreEntity> findByCode(String code);

    @Query("select m from MerchantStoreEntity m " +
            "left join fetch m.parent mp " +
            "left join fetch m.country mc " +
            "left join fetch m.currency mcr " +
            "left join fetch m.zone mz " +
            "left join fetch m.defaultLanguage md " +
            "left join fetch m.languages mls " +
            "where m.id = ?1")
    Optional<MerchantStoreEntity> getDetailById(UUID id);

    @Query("select distinct m from MerchantStoreEntity m " +
            "left join fetch m.parent mp " +
            "left join fetch m.country mc " +
            "left join fetch m.currency mcr " +
            "left join fetch m.zone mz " +
            "left join fetch m.defaultLanguage md " +
            "left join fetch m.languages mls " +
            "where mp.code = ?1")
    List<MerchantStoreEntity> getByParent(String code);

    @Query("SELECT COUNT(m) > 0 FROM MerchantStoreEntity m WHERE m.code = :code")
    boolean existsByCode(String code);

    @Query(value = "select distinct m from MerchantStoreEntity m " +
            "left join m.parent mp " +
            "where mp.code = ?1 or m.code = ?1")
    List<MerchantStoreEntity> findAllStoreNames(String storeCode);

    @Query(value = "select distinct m from MerchantStoreEntity m " +
            "left join m.parent mp " +
            "where mp.code in ?1 or m.code in ?1")
    List<MerchantStoreEntity> findAllStoreNames(List<String> storeCode);

    @Query(value = "select * from MerchantStore m where m.Code = ?1 or ?2 is null or m.ParentId = ?2", nativeQuery = true)
    List<MerchantStoreEntity> listByGroup(String storeCode, UUID id);
}
