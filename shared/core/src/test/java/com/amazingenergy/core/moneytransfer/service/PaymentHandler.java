package com.amazingenergy.core.moneytransfer.service;


import com.amazingenergy.core.Notification;
import com.amazingenergy.core.moneytransfer.domain.PaymentHistory;
import com.amazingenergy.core.moneytransfer.view.CreatePaymentRequest;

public interface PaymentHandler {
    String getPaymentMethod();
    Notification verify(CreatePaymentRequest createPaymentRequest);
    PaymentHistory pay(CreatePaymentRequest createPaymentRequest);
}
