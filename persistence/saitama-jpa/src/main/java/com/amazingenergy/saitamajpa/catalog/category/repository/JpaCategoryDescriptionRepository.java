package com.amazingenergy.saitamajpa.catalog.category.repository;

import com.amazingenergy.saitamajpa.catalog.category.entity.CategoryDescriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JpaCategoryDescriptionRepository extends JpaRepository<CategoryDescriptionEntity, UUID> {
    @Query("select c from CategoryDescriptionEntity c where c.category.id = ?1")
    List<CategoryDescriptionEntity> listByCategoryId(UUID categoryId);
}
