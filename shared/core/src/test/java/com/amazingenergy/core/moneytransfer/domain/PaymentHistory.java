package com.amazingenergy.core.moneytransfer.domain;

import com.amazingenergy.core.Notification;
import com.amazingenergy.core.domain.AggregateRoot;
import lombok.Getter;

import java.util.UUID;

@Getter
public class PaymentHistory extends AggregateRoot<UUID, PaymentHistory> {
    private static final String PAYMENT_STATUS_NOT_ALLOW_TO_CHANGE = "Payment with Status:{0} is not allow to change status";

    private final UUID accountId;
    private final double amount;
    private final PaymentMethod paymentMethod;
    private PaymentStatus status;
    private final boolean verified;

    public PaymentHistory(UUID accountId, double amount, PaymentMethod paymentMethod, boolean verified) {
        super(UUID.randomUUID());
        this.accountId = accountId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.status = PaymentStatus.PROCESSING;
        this.verified = verified;
    }

    public Notification cancel() {
        var notification = Notification.instance();
        if (this.status == PaymentStatus.CANCELLED || this.status == PaymentStatus.COMPLETED)
            return notification.addErrorCode("PAYMENT_STATUS_NOT_ALLOW_TO_CHANGE", PAYMENT_STATUS_NOT_ALLOW_TO_CHANGE, this.status);
        var currencyStatus = this.status;
        this.status = PaymentStatus.CANCELLED;
        this.addEvent(new PaymentStatusChangedEvent(this, currencyStatus, this.status));
        return notification;
    }

    @Override
    public String toString() {
        return "PaymentHistory { " + "[AccountId]: " + this.accountId +
                ", [Amount]: " + this.amount +
                ", [PaymentMethod]: " + this.paymentMethod.toString() +
                ", [Status]: " + this.status.toString() +
                ", [Verified]:" + this.verified + " }";
    }
}
