package com.amazingenergy.core.domain;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@AllArgsConstructor
public abstract class AggregateRoot<K extends Serializable & Comparable<K>, E extends AggregateRoot<K, ?>>
        implements Serializable, Comparable<E> {
    protected K id;

    private final List<Event> events = new ArrayList<>();

    public void addEvent(Event event) {
        events.add(event);
    }

    public List<Event> getEvents() {
        var result = new ArrayList<>(events);
        events.clear();
        return result;
    }

    public int compareTo(E o) {
        if (this == o) {
            return 0;
        }
        return this.getId().compareTo(o.getId());
    }
}
