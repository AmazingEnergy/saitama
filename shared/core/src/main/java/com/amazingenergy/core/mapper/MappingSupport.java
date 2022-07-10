package com.amazingenergy.core.mapper;

public interface MappingSupport<S, D> {
    D to(S source);

    S from(D destination);
}
