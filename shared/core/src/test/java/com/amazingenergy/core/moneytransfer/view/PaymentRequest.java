package com.amazingenergy.core.moneytransfer.view;

import com.amazingenergy.core.moneytransfer.domain.PaymentMethod;

public class PaymentRequest {
    private int AccountId;
    private double Amount;
    private PaymentMethod paymentMethod;

    public PaymentRequest(int accountId, double amount, PaymentMethod paymentMethod) {
        AccountId = accountId;
        Amount = amount;
        this.paymentMethod = paymentMethod;
    }

    public int getAccountId() {
        return AccountId;
    }

    public double getAmount() {
        return Amount;
    }

    public PaymentMethod getPaymentMethod() { return paymentMethod; }
}
