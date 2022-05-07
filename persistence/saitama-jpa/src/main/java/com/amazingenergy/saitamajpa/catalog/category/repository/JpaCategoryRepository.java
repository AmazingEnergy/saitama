package com.amazingenergy.saitamajpa.catalog.category.repository;

import com.amazingenergy.saitamajpa.catalog.category.entity.CategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaCategoryRepository extends JpaRepository<CategoryEntity, UUID>, CustomCategoryRepository {
    @Query(value = "select distinct c from CategoryEntity c " +
            "left join fetch c.descriptions cd " +
            "join fetch cd.language cdl " +
            "join fetch c.merchantStore cm " +
            "where cm.id=?1 and cdl.id=?2 and (cd.name like %?3% or ?3 is null) " +
            "order by c.lineage, c.sortOrder asc",
            countQuery = "select  count(c) from CategoryEntity c " +
                    "join c.descriptions cd " +
                    "join c.merchantStore cm " +
                    "where cm.id=?1 and cd.language.id=?2 and (cd.name like %?3% or ?3 is null)")
    Page<CategoryEntity> listByStore(UUID storeId, UUID languageId, String name, Pageable pageable);

    @Query("select c from CategoryEntity c " +
            "left join fetch c.descriptions cd " +
            "join fetch cd.language cdl " +
            "join fetch c.merchantStore cm " +
            "where cd.seUrl like ?2 and cm.id = ?1 " +
            "order by c.sortOrder asc")
    List<CategoryEntity> listByFriendlyUrl(UUID storeId, String friendlyUrl);

    @Query("select c from CategoryEntity c " +
            "left join fetch c.descriptions cd " +
            "join fetch cd.language cdl " +
            "join fetch c.merchantStore cm " +
            "where cd.seUrl=?2 and cm.id = ?1")
    CategoryEntity findByFriendlyUrl(UUID storeId, String friendlyUrl);

    @Query("select c from CategoryEntity c " +
            "left join fetch c.descriptions cd " +
            "join fetch cd.language cdl " +
            "join fetch c.merchantStore cm " +
            "where cd.name like %?2% and cdl.id=?3 and cm.id = ?1 " +
            "order by c.sortOrder asc")
    List<CategoryEntity> findByName(UUID storeId, String name, UUID languageId);

    @Query("select c from CategoryEntity c " +
            "left join fetch c.descriptions cd " +
            "join fetch cd.language cdl " +
            "join fetch c.merchantStore cm " +
            "where c.code=?2 and cm.id = ?1")
    CategoryEntity findByCode(UUID storeId, String code);

    @Query("select c from CategoryEntity c " +
            "left join fetch c.descriptions cd " +
            "join fetch cd.language cdl " +
            "join fetch c.merchantStore cm " +
            "where c.code in (?2) and cdl.id=?3 and cm.id = ?1 " +
            "order by c.sortOrder asc")
    List<CategoryEntity> findByCodes(UUID storeId, List<String> codes, UUID languageId);

    @Query("select c from CategoryEntity c " +
            "left join fetch c.descriptions cd " +
            "join fetch cd.language cdl " +
            "join fetch c.merchantStore cm " +
            "where c.id in (?2) and cdl.id=?3 and cm.id = ?1 " +
            "order by c.sortOrder asc")
    List<CategoryEntity> findByIds(UUID storeId, List<UUID> ids, UUID languageId);

    @Query("select c from CategoryEntity c " +
            "left join fetch c.descriptions cd " +
            "join fetch cd.language cdl " +
            "join fetch c.merchantStore cm " +
            "left join fetch c.categories " +
            "where cm.id=?1 and c.id = ?2 and cdl.id=?3")
    CategoryEntity findById(UUID storeId, UUID categoryId, UUID languageId);

    @Query("select c from CategoryEntity c " +
            "left join fetch c.descriptions cd " +
            "join fetch cd.language cdl " +
            "join fetch c.merchantStore cm " +
            "left join fetch c.categories " +
            "where c.id = ?1 and cdl.id=?2")
    CategoryEntity findByIdAndLanguage(UUID categoryId, UUID languageId);

    @Query("select c from CategoryEntity c " +
            "left join fetch c.descriptions cd " +
            "join fetch cd.language cdl " +
            "join fetch c.merchantStore cm " +
            "left join fetch c.categories " +
            "where c.id = ?1 and cm.id=?2")
    CategoryEntity findByIdAndStore(UUID categoryId, UUID storeId);

    @Query("select c from CategoryEntity c " +
            "left join fetch c.parent cp " +
            "left join fetch c.descriptions cd " +
            "join fetch cd.language cdl " +
            "join fetch c.merchantStore cm " +
            "left join fetch c.categories " +
            "where cm.code=?2 and c.id = ?1")
    CategoryEntity findById(UUID categoryId, String merchant);

    @Query("select c from CategoryEntity c " +
            "left join fetch c.parent cp " +
            "left join fetch c.descriptions cd " +
            "join fetch cd.language cdl " +
            "join fetch c.merchantStore cm " +
            "left join fetch c.categories " +
            "where c.id = ?1")
    Optional<CategoryEntity> findById(UUID categoryId);

    @Query("select c from CategoryEntity c " +
            "left join fetch c.descriptions cd " +
            "join fetch cd.language cdl " +
            "join fetch c.merchantStore cm " +
            "where cm.code=?1 and c.code=?2")
    CategoryEntity findByCode(String merchantStoreCode, String code);

    @Query("select c from CategoryEntity c " +
            "join fetch c.descriptions cd " +
            "join fetch cd.language cdl " +
            "join fetch c.merchantStore cm " +
            "where c.id=?1")
    CategoryEntity findOne(UUID categoryId);

    @Query("select distinct c from CategoryEntity c " +
            "left join fetch c.descriptions cd " +
            "join fetch cd.language cdl " +
            "join fetch c.merchantStore cm " +
            "where cm.id=?1 and c.lineage like %?2% " +
            "order by c.lineage, c.sortOrder asc")
    List<CategoryEntity> findByLineage(UUID merchantId, String linenage);

    @Query("select distinct c from CategoryEntity c " +
            "left join fetch c.descriptions cd " +
            "join fetch cd.language cdl " +
            "join fetch c.merchantStore cm " +
            "where cm.code= ?1 and c.lineage like %?2% " +
            "order by c.lineage, c.sortOrder asc")
    List<CategoryEntity> findByLineage(String storeCode, String linenage);

    @Query("select distinct c from CategoryEntity c " +
            "left join fetch c.descriptions cd " +
            "join fetch cd.language cdl " +
            "join fetch c.merchantStore cm " +
            "where cm.id=?1 and c.depth >= ?2 " +
            "order by c.lineage, c.sortOrder asc")
    List<CategoryEntity> findByDepth(UUID merchantId, int depth);

    @Query("select distinct c from CategoryEntity c " +
            "left join fetch c.descriptions cd " +
            "join fetch cd.language cdl " +
            "join fetch c.merchantStore cm " +
            "where cm.id=?1 and cdl.id=?3 and c.depth >= ?2 " +
            "order by c.lineage, c.sortOrder asc")
    List<CategoryEntity> findByDepth(UUID merchantId, int depth, UUID languageId);

    @Query("select distinct c from CategoryEntity c " +
            "left join fetch c.descriptions cd " +
            "join fetch cd.language cdl " +
            "join fetch c.merchantStore cm " +
            "where cm.id=?1 and cdl.id=?3 and c.depth >= ?2 and (?4 is null or cd.name like %?4%) " +
            "order by c.lineage, c.sortOrder asc")
    List<CategoryEntity> find(UUID merchantId, int depth, UUID languageId, String name);

    @Query("select distinct c from CategoryEntity c " +
            "left join fetch c.descriptions cd " +
            "join fetch cd.language cdl " +
            "join fetch c.merchantStore cm " +
            "where cm.id=?1 and cdl.id=?3 and c.depth >= ?2 and c.featured=true " +
            "order by c.lineage, c.sortOrder asc")
    List<CategoryEntity> findByDepthFilterByFeatured(UUID merchantId, int depth, UUID languageId);

    @Query("select distinct c from CategoryEntity c " +
            "left join fetch c.descriptions cd " +
            "join fetch cd.language cdl " +
            "join fetch c.merchantStore cm " +
            "left join fetch c.parent cp " +
            "where cp.id=?1 and cdl.id=?2 " +
            "order by c.lineage, c.sortOrder asc")
    List<CategoryEntity> findByParent(UUID parentId, UUID languageId);

    @Query("select distinct c from CategoryEntity c " +
            "left join fetch c.descriptions cd " +
            "join fetch cd.language cdl " +
            "join fetch c.merchantStore cm " +
            "where cm.id=?1 and cdl.id=?2 " +
            "order by c.lineage, c.sortOrder asc")
    List<CategoryEntity> findByStore(UUID merchantId, UUID languageId);

    @Query("select distinct c from CategoryEntity c " +
            "left join fetch c.descriptions cd " +
            "join fetch cd.language cdl " +
            "join fetch c.merchantStore cm " +
            "where cm.id=?1 " +
            "order by c.lineage, c.sortOrder asc")
    List<CategoryEntity> findByStore(UUID merchantId);

    @Query("select count(distinct c) from CategoryEntity as c where c.merchantStore.id=?1")
    int count(UUID storeId);
}
