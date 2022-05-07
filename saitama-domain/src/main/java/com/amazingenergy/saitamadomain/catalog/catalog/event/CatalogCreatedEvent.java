package com.amazingenergy.saitamadomain.catalog.catalog.event;

import com.amazingenergy.core.domain.Event;
import com.amazingenergy.saitamadomain.catalog.catalog.domain.Catalog;

public class CatalogCreatedEvent extends Event<Catalog> {
    public CatalogCreatedEvent(Catalog aggregateRoot) {
        super(aggregateRoot);
    }
}
