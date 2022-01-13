package com.amazingenergy.core.moneytransfer.repository;

import com.amazingenergy.core.moneytransfer.domain.Account;
import com.amazingenergy.core.moneytransfer.domain.AccountType;
import com.amazingenergy.core.publisher.EventPublisher;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@Repository
@Deprecated(since = "this class replaced by mocking bean in Unit Test")
public class AccountRepositoryImpl implements AccountRepository{

    private List<Account> accounts = new ArrayList<Account>() {{
        add(new Account(1, "Tom", AccountType.Business));
        add(new Account(2, "Luu", AccountType.Personal));
        add(new Account(3, "Yen", AccountType.Business));
    }};

    private final EventPublisher eventPublisher;

    public AccountRepositoryImpl(EventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public Optional<Account> getById(int id) {
        return accounts.stream().filter(a -> a.getId() == id).findFirst();
    }

    /**
     * Mimic an EventRunner concept in infrastructure bean implementation
     */
    public Account save(Account account) {
        // establish connection to database
        // perform a save change
        return account;
    }
}
