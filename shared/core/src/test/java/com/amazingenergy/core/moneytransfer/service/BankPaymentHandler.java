package com.amazingenergy.core.moneytransfer.service;

import com.amazingenergy.core.moneytransfer.domain.Account;
import com.amazingenergy.core.moneytransfer.domain.PaymentHistory;
import com.amazingenergy.core.moneytransfer.repository.AccountRepository;
import com.amazingenergy.core.moneytransfer.view.PaymentRequest;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("Bank")
public class BankPaymentHandler implements PaymentHandler, BeanNameAware {
    private String paymentMethod;

    private AccountRepository accountRepository;

    public BankPaymentHandler(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public String getPaymentMethod() {
        return paymentMethod;
    }

    @Override
    public boolean verify(PaymentRequest paymentRequest) {
        Optional<Account> accountOpt = accountRepository.getById(paymentRequest.getAccountId());

        if (!accountOpt.isPresent())
            return false;

        Account account = accountOpt.get();

        switch (account.getType()) {
            case Business:
                return paymentRequest.getAmount() < 5_000_000;
            case Personal:
                return paymentRequest.getAmount() < 1_000_000;
            case MTO:
                return paymentRequest.getAmount() < 100_000_000;
        }

        return false;
    }

    @Override
    public PaymentHistory pay(PaymentRequest paymentRequest) {
        System.out.println("Received a payment request " + paymentRequest);
        System.out.println("Pay with " + paymentMethod);
        System.out.println("[Processing] call Bank third-party. . . . . . . .");
        System.out.println(". . . . . . . .Completed!");

        Optional<Account> accountOpt = accountRepository.getById(paymentRequest.getAccountId());

        if (!accountOpt.isPresent())
            return null;

        Account account = accountOpt.get();

        PaymentHistory paymentHistory = new PaymentHistory(account.getId(), paymentRequest.getAmount(), paymentRequest.getPaymentMethod(), true);

        account.AddPayment(paymentHistory);

        Double earnedPoint = (paymentRequest.getAmount() / 100_000) * 0.05;
        account.earnBankPoint(earnedPoint);

        accountRepository.save(account);

        return paymentHistory;
    }

    /**
     * @Implement {@link BeanNameAware#setBeanName(String)}
     * @param name
     */
    @Override
    public void setBeanName(String name) {
        paymentMethod = name;
    }
}
