package com.amazingenergy.core.moneytransfer.view;

import com.amazingenergy.core.moneytransfer.domain.PaymentMethod;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CreatePaymentRequest {
    private final UUID accountId;
    private final double amount;
    private final PaymentMethod paymentMethod;

    public CreatePaymentRequest(UUID accountId, double amount, PaymentMethod paymentMethod) {
        this.accountId = accountId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
    }
}
