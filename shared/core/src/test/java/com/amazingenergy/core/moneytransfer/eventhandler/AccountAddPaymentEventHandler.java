package com.amazingenergy.core.moneytransfer.eventhandler;

import org.springframework.stereotype.Component;
import com.amazingenergy.core.eventhandler.EventHandler;
import com.amazingenergy.core.moneytransfer.domain.AccountAddPaymentEvent;

@Component
public class AccountAddPaymentEventHandler implements EventHandler<AccountAddPaymentEvent> {
    @Override
    public void onApplicationEvent(AccountAddPaymentEvent event) {
        System.out.println("\nReceived (" + AccountAddPaymentEvent.class.getSimpleName() + "): " +
                "\n\t\t" + event + "\n");
    }
}
