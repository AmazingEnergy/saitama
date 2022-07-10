package com.amazingenergy.saitamadomain.catalog.category.event;

import com.amazingenergy.core.domain.Event;
import com.amazingenergy.saitamadomain.catalog.category.domain.Category;

public class CategoryCreatedEvent extends Event<Category> {
    public CategoryCreatedEvent(Category category) {
        super(category);
    }
}
