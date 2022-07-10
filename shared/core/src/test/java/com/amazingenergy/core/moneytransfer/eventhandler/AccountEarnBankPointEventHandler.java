package com.amazingenergy.core.moneytransfer.eventhandler;

import org.springframework.stereotype.Component;
import com.amazingenergy.core.eventhandler.EventHandler;
import com.amazingenergy.core.moneytransfer.domain.AccountEarnBankPointEvent;

@Component
public class AccountEarnBankPointEventHandler implements EventHandler<AccountEarnBankPointEvent> {
    @Override
    public void onApplicationEvent(AccountEarnBankPointEvent event) {
        System.out.println("\nReceived (" + AccountEarnBankPointEvent.class.getSimpleName() + "): " +
                "\n\t\t" + event + "\n");
    }
}
