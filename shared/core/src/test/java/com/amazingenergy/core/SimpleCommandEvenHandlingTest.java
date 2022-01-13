package com.amazingenergy.core;

import com.amazingenergy.core.moneytransfer.domain.Account;
import com.amazingenergy.core.moneytransfer.domain.AccountType;
import com.amazingenergy.core.moneytransfer.domain.PaymentHistory;
import com.amazingenergy.core.moneytransfer.domain.PaymentMethod;
import com.amazingenergy.core.moneytransfer.view.PaymentRequest;
import com.amazingenergy.core.publisher.EventPublisher;
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

@SpringBootTest
public class SimpleCommandEvenHandlingTest {
    @Autowired
    private CommandManager commandManager;

    @Autowired
    private EventPublisher eventPublisher;

    @MockBean
    private AccountRepository accountRepository;

    /**
     * {@link AccountRepository} extends {@link com.amazingenergy.core.repository.Repository}.
     * However, the {@link com.amazingenergy.core.repository.RepositoryAfterAspect} is not work with {@link MockBean} annotation.
     * We have to explicitly handle domain event publishing with mock
     */
    @BeforeEach
    public void setUp() {
        List<Account> accounts = new ArrayList<Account>() {{
            add(new Account(1, "Tom", AccountType.Business));
            add(new Account(2, "Luu", AccountType.Personal));
            add(new Account(3, "Yen", AccountType.Business));
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
            var id = (int) invocation.getArgument(0);
            return accounts.stream().filter(a -> a.getId() == id).findFirst();
        }).when(accountRepository).getById(Mockito.any(Integer.class));
    }

    @Test
    void simpleTest_ShouldSuccess() {
        Account account = accountRepository.getById(1).get();
        Assertions.assertNotNull(account);
        Assertions.assertEquals(0, account.getPaymentHistories().size());
        System.out.println("Account before payment: \n" + account + "\n");

        PaymentHistory result = (PaymentHistory) commandManager.process(new PaymentRequest(1, 400_000, PaymentMethod.EWallet));
        Assertions.assertNotNull(result);
        System.out.println("Result from command: \n" + result + "\n");

        Assertions.assertEquals(1, account.getPaymentHistories().size());
        System.out.println("Account after payment: \n" + account + "\n");

        result = (PaymentHistory) commandManager.process(new PaymentRequest(1, 3_900_000, PaymentMethod.Bank));
        Assertions.assertNotNull(result);
        System.out.println("Result from command: \n" + result + "\n");

        Assertions.assertEquals(2, account.getPaymentHistories().size());
        System.out.println("Account after payment: \n" + account + "\n");
    }
}
