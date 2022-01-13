package com.amazingenergy.core.moneytransfer.domain;

import com.amazingenergy.core.domain.Event;

public class AccountAddPaymentEvent extends Event<Account> {
    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the {@link Account} on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public AccountAddPaymentEvent(Account source) {
        super(source);
    }

    @Override
    public String toString() {
        if (!(this.source instanceof Account))
            throw new IllegalStateException(AccountAddPaymentEvent.class.getSimpleName() + " contains invalid source");

        var account = (Account) this.source;
        var paymentCount = account.getPaymentHistories().size();

        if (paymentCount < 1)
            throw new IllegalStateException(AccountAddPaymentEvent.class.getSimpleName() + " contains invalid source");

        return "[Event]: " +
                "\n\t[Added Payment]: " + account.getPaymentHistories().get(paymentCount - 1);
    }
}