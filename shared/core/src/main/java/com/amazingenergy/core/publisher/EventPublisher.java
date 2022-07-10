package com.amazingenergy.core.publisher;

import com.amazingenergy.core.domain.Event;

public interface EventPublisher {
    void publish(Event event);
}
