package com.amazingenergy.core.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@AllArgsConstructor
public abstract class Entity<K extends Serializable & Comparable<K>, E extends Entity<K, ?>>
        implements Serializable, Comparable<E> {
    private K id;

    public int compareTo(E o) {
        if (this == o) {
            return 0;
        }
        return this.getId().compareTo(o.getId());
    }
}
