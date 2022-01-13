package com.amazingenergy.core.moneytransfer.domain;

import com.amazingenergy.core.domain.Event;

public class AccountEarnBankPointEvent extends Event<Account> {
    private final double earnedPoint;

    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public AccountEarnBankPointEvent(Account source, double earnedPoint) {
        super(source);
        this.earnedPoint = earnedPoint;
    }

    public double getEarnedPoint() {
        return earnedPoint;
    }

    @Override
    public String toString() {
        if (!(this.source instanceof Account))
            throw new IllegalStateException(AccountAddPaymentEvent.class.getSimpleName() + " contains invalid source");
        return "[Event]: " +
                "\n\t[Earned Bank Point]: " + this.earnedPoint;
    }
}