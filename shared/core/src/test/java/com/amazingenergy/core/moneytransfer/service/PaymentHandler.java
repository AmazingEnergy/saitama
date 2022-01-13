package com.amazingenergy.core.moneytransfer.service;


import com.amazingenergy.core.moneytransfer.domain.PaymentHistory;
import com.amazingenergy.core.moneytransfer.view.PaymentRequest;

public interface PaymentHandler {
    String getPaymentMethod();
    boolean verify(PaymentRequest paymentRequest);
    PaymentHistory pay(PaymentRequest paymentRequest);
}
