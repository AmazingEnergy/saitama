package com.amazingenergy.core.command;

import com.amazingenergy.core.genericstatus.StatusGenericHandler;

public interface InOnlyCommand<TIn> extends StatusGenericHandler {
    String getName();
    void execute(TIn input);
}
