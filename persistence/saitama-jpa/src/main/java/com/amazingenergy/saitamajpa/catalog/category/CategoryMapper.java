package com.amazingenergy.saitamajpa.catalog.category;

import com.amazingenergy.saitamadomain.catalog.category.domain.Category;
import com.amazingenergy.saitamadomain.catalog.category.domain.CategoryDescription;
import com.amazingenergy.saitamajpa.catalog.category.entity.CategoryDescriptionEntity;
import com.amazingenergy.saitamajpa.catalog.category.entity.CategoryEntity;
import org.mapstruct.Mapper;

@Mapper
public interface CategoryMapper {
    CategoryEntity to(Category source);

    Category from(CategoryEntity destination);

    CategoryDescriptionEntity to(CategoryDescription source);

    CategoryDescription from(CategoryDescriptionEntity destination);
}
