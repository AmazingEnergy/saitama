package com.amazingenergy.core;

import com.amazingenergy.core.command.InOnlyCommandManager;
import com.amazingenergy.core.moneytransfer.domain.*;
import com.amazingenergy.core.moneytransfer.view.CancelPaymentRequest;
import com.amazingenergy.core.moneytransfer.view.CreatePaymentRequest;
import com.amazingenergy.core.publisher.EventPublisher;
import com.amazingenergy.core.repository.AggregateRootRepository;
import com.amazingenergy.core.repository.AggregateRootRepositoryAfterAspect;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.amazingenergy.core.command.CommandManager;
import com.amazingenergy.core.moneytransfer.repository.AccountRepository;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootTest
public class SimpleCommandEvenHandlingTest {
    @Autowired
    private CommandManager commandManager;

    @Autowired
    private InOnlyCommandManager inOnlyCommandManager;

    @Autowired
    private EventPublisher eventPublisher;

    @MockBean
    private AccountRepository accountRepository;

    private List<Account> accounts;

    /**
     * {@link AccountRepository} extends {@link AggregateRootRepository}.
     * However, the {@link AggregateRootRepositoryAfterAspect} is not work with {@link MockBean} annotation.
     * We have to explicitly handle domain event publishing with mock
     */
    @BeforeEach
    public void setUp() {
        accounts = new ArrayList<Account>() {{
            add(new Account("Tom", AccountType.Business));
            add(new Account("Luu", AccountType.Personal));
            add(new Account("Yen", AccountType.Business));
        }};

        Mockito.doAnswer(invocation -> {
            accounts.forEach(account -> {
                var events = account.getEvents();
                events.forEach(this.eventPublisher::publish);
            });
            // establish connection to database
            // perform a save change
            return null;
        }).when(accountRepository).save(Mockito.any(Account.class));

        Mockito.doAnswer(invocation -> {
            var id = (UUID) invocation.getArgument(0);
            return accounts.stream().filter(a -> a.getId() == id).findFirst();
        }).when(accountRepository).findById(Mockito.any(UUID.class));
    }

    @Test
    void runCommand_AndTriggerEvent_ShouldSuccess() {
        var accountId = accounts.get(0).getId();

        Account account = accountRepository.findById(accountId).get();
        Assertions.assertNotNull(account);
        Assertions.assertEquals(0, account.getPaymentHistories().size());
        System.out.println("Account before payment: \n" + account + "\n");

        var firstPayment = (PaymentHistory) commandManager.process(new CreatePaymentRequest(accountId, 400_000, PaymentMethod.EWallet));
        Assertions.assertNotNull(firstPayment);
        System.out.println("Result from command: \n" + firstPayment + "\n");

        Assertions.assertEquals(1, account.getPaymentHistories().size());
        System.out.println("Account after payment: \n" + account + "\n");

        var secondPayment = (PaymentHistory) commandManager.process(new CreatePaymentRequest(accountId, 3_900_000, PaymentMethod.Bank));
        Assertions.assertNotNull(secondPayment);
        Assertions.assertNotEquals(firstPayment.getId(), secondPayment.getId());
        System.out.println("Result from command: \n" + secondPayment + "\n");

        Assertions.assertEquals(2, account.getPaymentHistories().size());
        System.out.println("Account after payment: \n" + account + "\n");

        var thirdPayment = (PaymentHistory) commandManager.process(new CreatePaymentRequest(accountId, 5_200_000, PaymentMethod.Unspecified));
        Assertions.assertNull(thirdPayment);
        Assertions.assertNotNull(commandManager.status.getErrors());
        Assertions.assertTrue(commandManager.status.hasErrors());
        Assertions.assertTrue(commandManager.status.getErrors().size() > 0);
    }

    @Test
    void runInOnlyCommand_AndTriggerEvent_ShouldSuccess() {
        var accountId = accounts.get(0).getId();

        Account account = accountRepository.findById(accountId).get();
        Assertions.assertNotNull(account);
        Assertions.assertEquals(0, account.getPaymentHistories().size());
        System.out.println("Account before payment: \n" + account + "\n");

        var firstPayment = (PaymentHistory) commandManager.process(new CreatePaymentRequest(accountId, 400_000, PaymentMethod.EWallet));
        Assertions.assertNotNull(firstPayment);
        System.out.println("Result from command: \n" + firstPayment + "\n");

        Assertions.assertEquals(1, account.getPaymentHistories().size());
        var processingPayment = account.getPaymentHistories().stream().findFirst().get();
        Assertions.assertEquals(firstPayment.getId(), processingPayment.getId());
        Assertions.assertEquals(PaymentStatus.PROCESSING, processingPayment.getStatus());
        System.out.println("Account after payment: \n" + account + "\n");

        inOnlyCommandManager.process(new CancelPaymentRequest(accountId, firstPayment.getId()));
        Assertions.assertFalse(inOnlyCommandManager.status.hasErrors());
        Assertions.assertEquals(0, inOnlyCommandManager.status.getErrors().size());

        Assertions.assertEquals(1, account.getPaymentHistories().size());
        var cancelledPayment = account.getPaymentHistories().stream().findFirst().get();
        Assertions.assertEquals(firstPayment.getId(), cancelledPayment.getId());
        Assertions.assertEquals(PaymentStatus.CANCELLED, cancelledPayment.getStatus());
        System.out.println("Account after cancel payment: \n" + account + "\n");

        inOnlyCommandManager.process(new CancelPaymentRequest(accountId, firstPayment.getId()));
        Assertions.assertTrue(inOnlyCommandManager.status.hasErrors());
        Assertions.assertTrue(inOnlyCommandManager.status.getErrors().size() > 0);
    }
}
