package com.amazingenergy.saitamajpa.user.repository;

import com.amazingenergy.saitamajpa.user.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaUserRepository extends JpaRepository<UserEntity, UUID>, CustomUserRepository {
    @Query(value = "select distinct u from UserEntity as u " +
            "left join fetch u.groups ug " +
            "left join fetch ug.permissions ugp " +
            "left join fetch u.defaultLanguage ud " +
            "join fetch u.merchantStore um " +
            "where um.code=?1 and (?2 is null or u.email like %?2%)",
            countQuery = "select count(distinct u) from UserEntity as u " +
                    "join u.groups ug " +
                    "join ug.permissions ugp " +
                    "join u.merchantStore um " +
                    "where um.code=?1 and (?2 is null or u.email like %?2%)")
    Page<UserEntity> listByStore(String storeCode, String email, Pageable pageable);

    @Query(value = "select distinct u from UserEntity as u " +
            "left join fetch u.groups ug " +
            "left join fetch ug.permissions ugp " +
            "left join fetch u.defaultLanguage ud " +
            "join fetch u.merchantStore um " +
            "where (?1 is null or u.email like %?1%)",
            countQuery = "select count(distinct u) from UserEntity as u " +
                    "join u.groups ug " +
                    "join ug.permissions ugp " +
                    "join u.merchantStore um " +
                    "where (?1 is null or u.email like %?1%)")
    Page<UserEntity> listAll(String email, Pageable pageable);

    @Query(value = "select distinct u from UserEntity as u " +
            "left join fetch u.groups ug " +
            "left join fetch ug.permissions ugp " +
            "left join fetch u.defaultLanguage ud " +
            "join fetch u.merchantStore um " +
            "where um.id in ?1 and (?2 is null or u.email like %?2%)",
            countQuery = "select count(distinct u) from UserEntity as u " +
                    "join u.groups ug " +
                    "join ug.permissions ugp " +
                    "join u.merchantStore um " +
                    "where um.id in ?1 and (?2 is null or u.email like %?2%)")
    Page<UserEntity> listByStoreIds(List<UUID> stores, String email, Pageable pageable);

    @Query("select distinct u from UserEntity as u " +
            "left join fetch u.groups ug " +
            "join fetch u.merchantStore um " +
            "left join fetch u.defaultLanguage ul " +
            "where u.username = ?1")
    Optional<UserEntity> findByUserName(String userName);

    @Query("select distinct u from UserEntity as u " +
            "left join fetch u.groups ug " +
            "join fetch u.merchantStore um " +
            "left join fetch u.defaultLanguage ul " +
            "where u.id = ?1 and um.code = ?2")
    Optional<UserEntity> findByUserId(UUID userId, String storeCode);

    @Query("select distinct u from UserEntity as u " +
            "left join fetch u.groups ug " +
            "join fetch u.merchantStore um " +
            "left join fetch u.defaultLanguage ul " +
            "where u.username = ?1 and um.code = ?2")
    Optional<UserEntity> findByUserName(String userName, String storeCode);

    @Query("select distinct u from UserEntity as u " +
            "left join fetch u.groups ug " +
            "join fetch u.merchantStore um " +
            "left join fetch u.defaultLanguage ul " +
            "where u.id = ?1")
    Optional<UserEntity> findOne(UUID id);

    @Query("select distinct u from UserEntity as u " +
            "left join fetch u.groups ug " +
            "join fetch u.merchantStore um " +
            "left join fetch u.defaultLanguage ul " +
            "order by u.id")
    List<UserEntity> findAll();

    @Query("select distinct u from UserEntity as u " +
            "left join fetch u.groups ug " +
            "join fetch u.merchantStore um " +
            "left join fetch u.defaultLanguage ul " +
            "where um.id = ?1 order by u.id")
    List<UserEntity> findByStore(UUID storeId);

    @Query("select distinct u from UserEntity as u " +
            "left join fetch u.groups ug " +
            "join fetch u.merchantStore um " +
            "left join fetch u.defaultLanguage ul " +
            "where u.id= ?1 and um.code = ?2")
    Optional<UserEntity> findByUserAndStore(UUID userId, String storeCode);

    @Query("select distinct u from UserEntity as u "
            + "left join fetch u.groups ug "
            + "join fetch u.merchantStore um "
            + "left join fetch u.defaultLanguage ul "
            + "where u.credentialsResetRequest.credentialsRequest = ?1 and um.code = ?2 ")
    Optional<UserEntity> findByResetPasswordToken(String token, String storeCode);
}
