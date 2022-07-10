package com.amazingenergy.core.command;

import com.amazingenergy.core.genericstatus.StatusGeneric;
import com.amazingenergy.core.genericstatus.StatusGenericHandler;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public abstract class CommandManager<TIn, TOut> {
    public StatusGenericHandler<TOut> status;

    public TOut process(TIn commandState) {
        Command<TIn, TOut> command = createCommand();
        status = command;
        var result = command.execute(commandState);
        status.setResult(result);
        return result;
    }

    /**
     * Method Injection (demo)
     * <p>
     * Lookup method injection is the ability of the container to override methods on container-managed
     * beans and return the lookup result for another named bean in the container. The lookup
     * typically involves a prototype bean. The Spring Framework implements this method injection by
     * using bytecode generation from the CGLIB library to dynamically generate a subclass that
     * overrides the method.
     * </p>
     * @return Command
     */
    @Lookup
    protected abstract Command<TIn, TOut> createCommand();
}
