package com.amazingenergy.saitamadomain.payment.domain;

import com.amazingenergy.core.domain.AggregateRoot;
import com.amazingenergy.core.domain.AuditSection;
import com.amazingenergy.saitamadomain.order.domain.Order;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class Transaction extends AggregateRoot<UUID, Transaction> {
    private Order order;
    private BigDecimal amount;
    private Date transactionDate;
    private TransactionType transactionType;
    private PaymentType paymentType;
    private String details;
    private Map<String,String> transactionDetails= new HashMap<>();
    private AuditSection auditSection = new AuditSection();

    public Transaction() {
        super(UUID.randomUUID());
    }
}
