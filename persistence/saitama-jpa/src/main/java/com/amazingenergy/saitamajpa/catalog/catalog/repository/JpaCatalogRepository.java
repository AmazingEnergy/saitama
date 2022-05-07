package com.amazingenergy.saitamajpa.catalog.catalog.repository;

import com.amazingenergy.saitamajpa.catalog.catalog.entity.CatalogEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaCatalogRepository extends JpaRepository<CatalogEntity, UUID> {
    @Query(value = "select distinct c from CatalogEntity c " +
            "join fetch c.merchantStore cm left " +
            "join fetch c.entry ce " +
            "where cm.id=?1 and (?2 is null or c.code like %?2%)",
            countQuery = "select count(c) from CatalogEntity c " +
                    "join c.merchantStore cm " +
                    "join c.entry ce " +
                    "where cm.id=?1 and (?2 is null or c.code like %?2%)")
    Page<CatalogEntity> listByStore(UUID storeId, String code, Pageable pageable);

    @Query("select c from CatalogEntity c "
            + "join c.merchantStore cm "
            + "left join fetch c.entry ce "
            //+ "left join fetch ce.product cep "
            + "left join fetch ce.category cec where c.id=?1 and cm.id = ?2")
    Optional<CatalogEntity> findById(UUID catalogId, UUID merchantId);

    @Query("select c from CatalogEntity c "
            + "join c.merchantStore cm "
            + "left join fetch c.entry ce "
            //+ "left join fetch ce.product cep "
            + "left join fetch ce.category cec where c.code=?1 and cm.id = ?2")
    Optional<CatalogEntity> findByCode(String code, UUID merchantId);

    @Query("SELECT COUNT(c) > 0 FROM CatalogEntity c "
            + "join c.merchantStore cm  "
            + "WHERE c.code = ?1 and cm.id = ?2")
    boolean existsByCode(String code, UUID merchantId);
}
