package com.amazingenergy.core.moneytransfer.command;

import com.amazingenergy.core.command.BaseCommand;
import com.amazingenergy.core.moneytransfer.domain.PaymentHistory;
import com.amazingenergy.core.moneytransfer.service.PaymentHandlerResolver;
import com.amazingenergy.core.moneytransfer.view.CreatePaymentRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Scope("prototype")
public class CreatePaymentCommandImpl extends BaseCommand<PaymentHistory> implements CreatePaymentCommand {

    private static final String INVALID_PAYMENT_METHOD = "Invalid Given PaymentMethod:{0}";

    private final PaymentHandlerResolver paymentHandlerResolver;

    public CreatePaymentCommandImpl(PaymentHandlerResolver paymentHandlerResolver) {
        this.paymentHandlerResolver = paymentHandlerResolver;
    }

    @Override
    public String getName() {
        return CreatePaymentCommandImpl.class.getSimpleName();
    }

    @Override
    public PaymentHistory execute(CreatePaymentRequest createPaymentRequest) {
        System.out.println("Execute " + CreatePaymentCommandImpl.class.getSimpleName());

        var paymentHandler = paymentHandlerResolver.getPaymentHandler(createPaymentRequest.getPaymentMethod().toString());
        if (paymentHandler == null) {
            addError("INVALID_PAYMENT_METHOD", INVALID_PAYMENT_METHOD, createPaymentRequest.getPaymentMethod().toString());
            return null;
        }

        var notification = paymentHandler.verify(createPaymentRequest);
        if (notification.hasError()) {
            addError(notification);
            log.warn("Account {} request to create invalid payment", createPaymentRequest.getAccountId());
            return null;
        }

        return paymentHandler.pay(createPaymentRequest);
    }
}