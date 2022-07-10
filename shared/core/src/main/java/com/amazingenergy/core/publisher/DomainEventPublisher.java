package com.amazingenergy.core.publisher;

import com.amazingenergy.core.domain.Event;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class DomainEventPublisher implements EventPublisher, ApplicationContextAware {
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void publish(Event event) {
        applicationContext.publishEvent(event);
    }
}

