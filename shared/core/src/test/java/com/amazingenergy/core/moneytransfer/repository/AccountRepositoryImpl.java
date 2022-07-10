package com.amazingenergy.core.moneytransfer.repository;

import com.amazingenergy.core.moneytransfer.domain.Account;
import com.amazingenergy.core.moneytransfer.domain.AccountType;
import com.amazingenergy.core.publisher.EventPublisher;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

//@Repository
@Deprecated(since = "this class replaced by mocking bean in Unit Test")
public class AccountRepositoryImpl implements AccountRepository {

    private List<Account> accounts = new ArrayList<Account>() {{
        add(new Account("Tom", AccountType.Business));
        add(new Account("Luu", AccountType.Personal));
        add(new Account("Yen", AccountType.Business));
    }};

    private final EventPublisher eventPublisher;

    public AccountRepositoryImpl(EventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    /**
     * Mimic an EventRunner concept in infrastructure bean implementation
     */
    public Account save(Account account) {
        // establish connection to database
        // perform a save change
        return account;
    }

    @Override
    public List<Account> saveAll(Iterable<Account> entities) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(Account entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void create(Account entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Account entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Account> findById(UUID id) {
        return accounts.stream().filter(a -> a.getId() == id).findFirst();
    }

    @Override
    public List<Account> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Long count() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void flush() {
        throw new UnsupportedOperationException();
    }
}
