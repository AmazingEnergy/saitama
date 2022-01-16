package com.amazingenergy.core.moneytransfer.command;

import com.amazingenergy.core.command.BaseCommand;
import com.amazingenergy.core.command.Command;
import com.amazingenergy.core.moneytransfer.domain.PaymentHistory;
import com.amazingenergy.core.moneytransfer.service.PaymentHandler;
import com.amazingenergy.core.moneytransfer.service.PaymentHandlerResolver;
import com.amazingenergy.core.moneytransfer.view.PaymentRequest;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Dictionary;
import java.util.Hashtable;

@Component
@Scope("prototype")
public class PaymentCommand extends BaseCommand<PaymentHistory> implements Command<PaymentRequest, PaymentHistory> {

    private static final String INVALID_PAYMENT_METHOD = "Invalid Given PaymentMethod:{0}";

    private final PaymentHandlerResolver paymentHandlerResolver;

    public PaymentCommand(PaymentHandlerResolver paymentHandlerResolver) {
        this.paymentHandlerResolver = paymentHandlerResolver;
    }

    @Override
    public String getName() {
        return PaymentCommand.class.getSimpleName();
    }

    @Override
    public PaymentHistory execute(PaymentRequest paymentRequest) {
        System.out.println("Execute " + PaymentCommand.class.getSimpleName());

        PaymentHandler paymentHandler = paymentHandlerResolver.getPaymentHandler(paymentRequest.getPaymentMethod().toString());
        if (paymentHandler == null) {
            addError(INVALID_PAYMENT_METHOD, INVALID_PAYMENT_METHOD, paymentRequest.getPaymentMethod().toString());
            return null;
        }

        if (!paymentHandler.verify(paymentRequest)) {
            System.out.println("Payment request is not valid");
            return null;
        }

        return paymentHandler.pay(paymentRequest);
    }
}