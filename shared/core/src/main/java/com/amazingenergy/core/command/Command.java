package com.amazingenergy.core.command;

import com.amazingenergy.core.genericstatus.StatusGenericHandler;

public interface Command<TIn, TOut> extends StatusGenericHandler<TOut> {
    String getName();
    TOut execute(TIn input);
}
