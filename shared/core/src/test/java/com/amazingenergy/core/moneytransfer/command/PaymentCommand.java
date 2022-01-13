package com.amazingenergy.core.moneytransfer.command;

import com.amazingenergy.core.command.Command;
import com.amazingenergy.core.moneytransfer.domain.PaymentHistory;
import com.amazingenergy.core.moneytransfer.service.PaymentHandler;
import com.amazingenergy.core.moneytransfer.service.PaymentHandlerResolver;
import com.amazingenergy.core.moneytransfer.view.PaymentRequest;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class PaymentCommand implements Command<PaymentRequest, PaymentHistory> {
    private final PaymentHandlerResolver paymentHandlerResolver;

    private PaymentRequest paymentRequest;

    public PaymentCommand(PaymentHandlerResolver paymentHandlerResolver) {
        this.paymentHandlerResolver = paymentHandlerResolver;
    }

    @Override
    public String getName() {
        return PaymentCommand.class.getSimpleName();
    }

    @Override
    public void setState(PaymentRequest state) {
        this.paymentRequest = state;
    }

    @Override
    public PaymentHistory execute() {
        System.out.println("Execute " + PaymentCommand.class.getSimpleName());

        PaymentHandler paymentHandler = paymentHandlerResolver.getPaymentHandler(paymentRequest.getPaymentMethod().toString());
        if (paymentHandler == null) {
            throw new IllegalArgumentException("Invalid " + PaymentRequest.class.getSimpleName());
        }

        if (!paymentHandler.verify(paymentRequest)) {
            System.out.println("Payment request is not valid");
            return null;
        }

        return paymentHandler.pay(paymentRequest);
    }
}