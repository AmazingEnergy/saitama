package com.amazingenergy.saitamaappservice.catalog.catalog.handler;

import com.amazingenergy.core.eventhandler.EventHandler;
import com.amazingenergy.saitamadomain.catalog.catalog.event.CatalogCreatedEvent;
import org.springframework.stereotype.Component;

@Component
public class CatalogCreatedEventHandler implements EventHandler<CatalogCreatedEvent> {
    @Override
    public void onApplicationEvent(CatalogCreatedEvent event) {
        System.out.println("\nReceived (" + CatalogCreatedEvent.class.getSimpleName() + "): " +
                "\n\t\t" + event + "\n");
    }
}
