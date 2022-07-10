package com.amazingenergy.saitamadomain.payment.repository;

import com.amazingenergy.core.repository.AggregateRootRepository;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import com.amazingenergy.saitamadomain.order.domain.Order;
import com.amazingenergy.saitamadomain.payment.domain.Transaction;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends AggregateRootRepository<UUID, Transaction> {
    /**
     * Obtain a previous transaction that has type authorize for a give order
     */
    Transaction getCapturableTransaction(Order order);

    Transaction getRefundableTransaction(Order order);

    List<Transaction> listTransactions(Order order);

    List<Transaction> listTransactions(Date startDate, Date endDate);

    Transaction lastTransaction(Order order, MerchantStore store);
}
