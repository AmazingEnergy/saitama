package com.amazingenergy.core.moneytransfer.domain;

import com.amazingenergy.core.domain.AggregateRoot;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class Account extends AggregateRoot<UUID, Account> {
    private final String name;
    private final AccountType type;
    private final List<PaymentHistory> paymentHistories;
    private double memberBankPoint;
    private double memberEWalletPoint;

    public Account(String name, AccountType type) {
        super(UUID.randomUUID());
        this.name = name;
        this.type = type;
        this.paymentHistories = new ArrayList<>();
        this.memberBankPoint = 0;
        this.memberEWalletPoint = 0;
    }

    public void AddPayment(PaymentHistory paymentHistory) {
        this.addEvent(new AccountAddPaymentEvent(this));
        paymentHistories.add(paymentHistory);
    }

    public void earnBankPoint(double point) {
        this.addEvent(new AccountEarnBankPointEvent(this, point));
        this.memberBankPoint += point;
    }

    public void earnEWalletPoint(double point) {
        this.addEvent(new AccountEarnEWalletPointEvent(this, point));
        this.memberEWalletPoint += point;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Account { " + "[Id]:").append(this.id)
                .append(", [Name]:").append(this.name)
                .append(", [PaymentCount]:").append((long) this.paymentHistories.size())
                .append(", [BankPoint]:").append(this.memberBankPoint)
                .append(", [EWalletPoint]:").append(this.memberEWalletPoint)
                .append(" }\n");

        this.paymentHistories.forEach(p -> stringBuilder.append(p).append("\n"));

        return stringBuilder.toString();
    }
}