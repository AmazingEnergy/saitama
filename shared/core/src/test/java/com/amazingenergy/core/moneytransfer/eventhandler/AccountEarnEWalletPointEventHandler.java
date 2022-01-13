package com.amazingenergy.core.moneytransfer.eventhandler;

import org.springframework.stereotype.Component;
import com.amazingenergy.core.eventhandler.EventHandler;
import com.amazingenergy.core.moneytransfer.domain.AccountEarnEWalletPointEvent;

@Component
public class AccountEarnEWalletPointEventHandler implements EventHandler<AccountEarnEWalletPointEvent> {
    @Override
    public void onApplicationEvent(AccountEarnEWalletPointEvent event) {
        System.out.println("\nReceived (" + AccountEarnEWalletPointEvent.class.getSimpleName() + "):" +
                "\n\t\t" + event + "\n");
    }
}
