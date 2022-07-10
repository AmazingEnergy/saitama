package com.amazingenergy.saitamadomain.payment.domain;

import java.util.Arrays;
import java.util.Optional;

public enum PaymentType {
    CREDIT_CARD("creditcard"),
    FREE("free"),
    COD("cod"),
    MONEY_ORDER("moneyorder"),
    PAYPAL("paypal"),

    INVOICE("invoice"),
    DIRECT_BANK("directbank"),
    PAYMENT_PLAN("paymentplan"),
    ACCOUNT_CREDIT("accountcredit");

    private String paymentType;

    PaymentType(String type) {
        paymentType = type;
    }

    public static Optional<PaymentType> fromString(String text) {
        return Arrays.stream(PaymentType.values()).filter(type -> text.equalsIgnoreCase(type.name())).findFirst();
    }
}
