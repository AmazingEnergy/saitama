package com.amazingenergy.saitamajpa.catalog.product.repository;

import com.amazingenergy.saitamajpa.catalog.product.entity.attribute.ProductAttributeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaProductAttributeRepository extends JpaRepository<ProductAttributeEntity, UUID> {
    @Query("select p from ProductAttributeEntity p " +
            "join fetch p.product pr " +
            "left join fetch p.option po " +
            "left join fetch p.optionValue pov " +
            "left join fetch po.descriptions pod " +
            "left join fetch pov.descriptions povd " +
            "left join fetch po.merchantStore " +
            "where p.id = ?1")
    Optional<ProductAttributeEntity> findOne(UUID id);

    @Query("select p from ProductAttributeEntity p " +
            "join fetch p.product pr " +
            "left join fetch p.option po " +
            "left join fetch p.optionValue pov " +
            "left join fetch po.descriptions pod " +
            "left join fetch pov.descriptions povd " +
            "left join fetch po.merchantStore pom " +
            "where pom.id = ?1 and po.id = ?2")
    List<ProductAttributeEntity> findByOptionId(UUID storeId, UUID id);

    @Query("select distinct p from ProductAttributeEntity p " +
            "join fetch p.product pr " +
            "left join fetch p.option po " +
            "left join fetch p.optionValue pov " +
            "left join fetch po.descriptions pod " +
            "left join fetch pov.descriptions povd " +
            "left join fetch po.merchantStore pom " +
            "where pom.id = ?1 and pov.id = ?2")
    List<ProductAttributeEntity> findByOptionValueId(UUID storeId, UUID id);

    @Query("select distinct p from ProductAttributeEntity p " +
            "join fetch p.product pr " +
            "left join fetch p.option po " +
            "left join fetch p.optionValue pov " +
            "left join fetch po.descriptions pod " +
            "left join fetch pov.descriptions povd " +
            "left join fetch pov.merchantStore povm " +
            "where povm.id = ?1 and pr.id = ?2 and p.id in ?3")
    List<ProductAttributeEntity> findByAttributeIds(UUID storeId, UUID productId, List<UUID> ids);

    @Query("select distinct p from ProductAttributeEntity p " +
            "join fetch p.product pr " +
            "left join fetch p.option po " +
            "left join fetch p.optionValue pov " +
            "left join fetch po.descriptions pod " +
            "left join fetch pov.descriptions povd " +
            "left join fetch po.merchantStore pom " +
            "where pom.id = ?1 and pr.id = ?2 and povd.language.id = ?3")
    List<ProductAttributeEntity> findByProductId(UUID storeId, UUID productId, UUID languageId);

    @Query("select distinct p from ProductAttributeEntity p " +
            "join fetch p.product pr " +
            "left join fetch p.option po " +
            "left join fetch p.optionValue pov " +
            "left join fetch po.descriptions pod " +
            "left join fetch pov.descriptions povd " +
            "left join fetch po.merchantStore pom " +
            "where pom.id = ?1 and pr.id = ?2")
    List<ProductAttributeEntity> findByProductId(UUID storeId, UUID productId);

    @Query(value = "select distinct p from ProductAttributeEntity p " +
            "join fetch p.product pr " +
            "left join fetch pr.categories prc " +
            "left join fetch p.option po " +
            "left join fetch p.optionValue pov " +
            "left join fetch po.descriptions pod " +
            "left join fetch pov.descriptions povd " +
            "left join fetch po.merchantStore pom " +
            "where pom.id = ?1 and prc.id IN (select c.id from CategoryEntity c where c.lineage like ?2% and povd.language.id = ?3)")
    List<ProductAttributeEntity> findOptionsByCategoryLineage(UUID storeId, String lineage, UUID languageId);
}
