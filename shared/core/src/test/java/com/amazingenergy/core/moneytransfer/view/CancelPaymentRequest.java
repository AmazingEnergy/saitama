package com.amazingenergy.core.moneytransfer.view;

import lombok.Getter;

import java.util.UUID;

@Getter
public class CancelPaymentRequest {
    private final UUID accountId;
    private final UUID paymentId;

    public CancelPaymentRequest(UUID accountId, UUID paymentId) {
        this.accountId = accountId;
        this.paymentId = paymentId;
    }
}
