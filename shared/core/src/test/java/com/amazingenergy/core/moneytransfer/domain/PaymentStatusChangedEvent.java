package com.amazingenergy.core.moneytransfer.domain;

import com.amazingenergy.core.domain.Event;
import lombok.Getter;

@Getter
public class PaymentStatusChangedEvent extends Event<PaymentHistory> {
    private final PaymentStatus fromStatus;
    private final PaymentStatus toStatus;

    public PaymentStatusChangedEvent(PaymentHistory paymentHistory, PaymentStatus fromStatus, PaymentStatus toStatus) {
        super(paymentHistory);
        this.fromStatus = fromStatus;
        this.toStatus = toStatus;
    }
}
