package com.amazingenergy.saitamadomain.user.repository;

import com.amazingenergy.core.domain.AggregateRoot;
import com.amazingenergy.core.repository.AggregateRootRepository;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import com.amazingenergy.saitamadomain.user.domain.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends AggregateRootRepository<UUID, User> {
    Optional<User> getByUserName(String userName);

    Optional<User> getByUserName(String userName, String storeCode);

    List<User> listUser();

    Optional<User> getById(UUID id, MerchantStore store);

    Optional<User> getByPasswordResetToken(String storeCode, String token);

    /**
     * Create or update a User
     */
    void saveOrUpdate(User user);

    List<User> listByStore(MerchantStore store);

    Optional<User> findByStore(UUID userId, String storeCode);

    Optional<User> findByResetPasswordToken(String userName, String token, MerchantStore store);
}
