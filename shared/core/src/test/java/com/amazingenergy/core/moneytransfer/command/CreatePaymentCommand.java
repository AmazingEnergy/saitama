package com.amazingenergy.core.moneytransfer.command;

import com.amazingenergy.core.command.Command;
import com.amazingenergy.core.moneytransfer.domain.PaymentHistory;
import com.amazingenergy.core.moneytransfer.view.CreatePaymentRequest;

public interface CreatePaymentCommand extends Command<CreatePaymentRequest, PaymentHistory> {
}
