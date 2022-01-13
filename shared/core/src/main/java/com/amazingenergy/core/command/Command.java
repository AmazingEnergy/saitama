package com.amazingenergy.core.command;

public interface Command<TIn, TOut> {
    String getName();
    void setState(TIn state);
    TOut execute();
}
