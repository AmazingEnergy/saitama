package com.amazingenergy.saitamadomain.payment.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    private PaymentType paymentType;
    private TransactionType transactionType = TransactionType.AUTHORIZE_CAPTURE;
    private String moduleName;
    private Currency currency;
    private BigDecimal amount;
    private Map<String,String> paymentMetaData = null;
}
