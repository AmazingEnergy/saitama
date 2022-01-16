package com.amazingenergy.core.moneytransfer.command;

import com.amazingenergy.core.command.BaseCommand;
import com.amazingenergy.core.moneytransfer.domain.Account;
import com.amazingenergy.core.moneytransfer.repository.AccountRepository;
import com.amazingenergy.core.moneytransfer.view.CancelPaymentRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@Scope("prototype")
public class CancelPaymentCommandImpl extends BaseCommand implements CancelPaymentCommand {
    private static final String ACCOUNT_NOT_FOUND = "Account Id:{0} is not found.";
    private static final String PAYMENT_NOT_FOUND = "Payment Id:{0} is not found.";

    private final AccountRepository accountRepository;

    public CancelPaymentCommandImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public String getName() {
        return CancelPaymentCommandImpl.class.getSimpleName();
    }

    @Override
    public void execute(CancelPaymentRequest input) {
        Optional<Account> accountOpt = accountRepository.findById(input.getAccountId());

        if (accountOpt.isEmpty()) {
            addError("ACCOUNT_NOT_FOUND", ACCOUNT_NOT_FOUND, input.getAccountId());
            return;
        }

        Account account = accountOpt.get();
        var paymentOpt = account.getPaymentHistories().stream().filter(p -> p.getId().equals(input.getPaymentId())).findFirst();
        if (paymentOpt.isEmpty()) {
            addError("PAYMENT_NOT_FOUND", PAYMENT_NOT_FOUND, input.getPaymentId());
            return;
        }

        var payment = paymentOpt.get();
        var updateNote = payment.cancel();
        if (updateNote.hasError())
            addError(updateNote);
    }
}
