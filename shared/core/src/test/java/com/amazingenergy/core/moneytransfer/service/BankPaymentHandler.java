package com.amazingenergy.core.moneytransfer.service;

import com.amazingenergy.core.Notification;
import com.amazingenergy.core.moneytransfer.domain.Account;
import com.amazingenergy.core.moneytransfer.domain.PaymentHistory;
import com.amazingenergy.core.moneytransfer.repository.AccountRepository;
import com.amazingenergy.core.moneytransfer.view.CreatePaymentRequest;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("Bank")
public class BankPaymentHandler implements PaymentHandler, BeanNameAware {
    private static final String ACCOUNT_NOT_FOUND = "Account Id:{0} is not found.";
    private static final String INVALID_PAYMENT_AMOUNT = "Account Type:{0} is not allow to create payment with Amount:{1}.";

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
    public Notification verify(CreatePaymentRequest createPaymentRequest) {
        var notification = Notification.instance();

        Optional<Account> accountOpt = accountRepository.findById(createPaymentRequest.getAccountId());

        if (accountOpt.isEmpty())
            return notification.addErrorCode("ACCOUNT_NOT_FOUND", ACCOUNT_NOT_FOUND, createPaymentRequest.getAccountId());

        Account account = accountOpt.get();

        var isValidAmount = false;
        switch (account.getType()) {
            case Business:
                isValidAmount = createPaymentRequest.getAmount() < 5_000_000;
                break;
            case Personal:
                isValidAmount = createPaymentRequest.getAmount() < 1_000_000;
                break;
            case MTO:
                isValidAmount = createPaymentRequest.getAmount() < 100_000_000;
                break;
        }

        return isValidAmount
                ? notification
                : notification.addErrorCode(INVALID_PAYMENT_AMOUNT, INVALID_PAYMENT_AMOUNT, account.getType(), createPaymentRequest.getAmount());
    }

    @Override
    public PaymentHistory pay(CreatePaymentRequest createPaymentRequest) {
        System.out.println("Received a payment request " + createPaymentRequest);
        System.out.println("Pay with " + paymentMethod);
        System.out.println("[Processing] call Bank third-party. . . . . . . .");
        System.out.println(". . . . . . . .Completed!");

        Optional<Account> accountOpt = accountRepository.findById(createPaymentRequest.getAccountId());

        if (accountOpt.isEmpty())
            return null;

        Account account = accountOpt.get();

        PaymentHistory paymentHistory = new PaymentHistory(account.getId(), createPaymentRequest.getAmount(), createPaymentRequest.getPaymentMethod(), true);

        account.AddPayment(paymentHistory);

        Double earnedPoint = (createPaymentRequest.getAmount() / 100_000) * 0.05;
        account.earnBankPoint(earnedPoint);

        accountRepository.save(account);

        return paymentHistory;
    }

    /**
     * @param name
     * @Implement {@link BeanNameAware#setBeanName(String)}
     */
    @Override
    public void setBeanName(String name) {
        paymentMethod = name;
    }
}
