package com.amazingenergy.core.moneytransfer.domain;

import com.amazingenergy.core.domain.Event;
import lombok.Getter;

@Getter
public class AccountEarnEWalletPointEvent extends Event<Account> {
    private final double earnedPoint;

    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the {@link Account} on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public AccountEarnEWalletPointEvent(Account source, double earnedPoint) {
        super(source);
        this.earnedPoint = earnedPoint;
    }

    @Override
    public String toString() {
        if (!(this.source instanceof Account))
            throw new IllegalStateException(AccountAddPaymentEvent.class.getSimpleName() + " contains invalid source");
        return "[Event]: " +
                "\n\t[Earned EWallet Point]: " + this.earnedPoint;
    }
}
