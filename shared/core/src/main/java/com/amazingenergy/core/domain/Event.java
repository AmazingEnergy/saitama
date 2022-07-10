package com.amazingenergy.core.domain;

import org.springframework.context.ApplicationEvent;

import java.time.Clock;

public class Event<T extends AggregateRoot> extends ApplicationEvent {
    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the {@link AggregateRoot} on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public Event(T aggregateRoot) {
        super(aggregateRoot);
    }

    public Event(T aggregateRoot, Clock clock) {
        super(aggregateRoot, clock);
    }
}
