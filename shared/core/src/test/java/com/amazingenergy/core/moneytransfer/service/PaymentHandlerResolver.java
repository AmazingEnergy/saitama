package com.amazingenergy.core.moneytransfer.service;

import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PaymentHandlerResolver {
    private final Set<PaymentHandler> paymentHandlers;

    public PaymentHandlerResolver(Set<PaymentHandler> paymentHandlers) {
        this.paymentHandlers = paymentHandlers;
    }

    public PaymentHandler getPaymentHandler(String paymentMethod) {
        var foundHander = paymentHandlers.stream().filter(handler -> handler.getPaymentMethod().equalsIgnoreCase(paymentMethod)).findFirst();
        return foundHander.orElse(null);
    }
}
