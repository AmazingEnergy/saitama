package com.amazingenergy.core.command;

import com.amazingenergy.core.genericstatus.StatusGenericHandler;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public abstract class InOnlyCommandManager<TIn> {
    public StatusGenericHandler status;

    public void process(TIn commandState) {
        InOnlyCommand<TIn> command = createCommand();
        status = command;
        command.execute(commandState);
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
    protected abstract InOnlyCommand<TIn> createCommand();
}
