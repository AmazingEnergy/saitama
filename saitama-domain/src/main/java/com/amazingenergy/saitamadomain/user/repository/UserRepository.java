package com.amazingenergy.saitamadomain.user.repository;

import com.amazingenergy.core.domain.AggregateRoot;
import com.amazingenergy.core.repository.AggregateRootRepository;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import com.amazingenergy.saitamadomain.user.domain.User;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends AggregateRootRepository<UUID, User> {
    User getByUserName(String userName);

    User getByUserName(String userName, String storeCode);

    List<User> listUser();

    User getById(Long id, MerchantStore store);

    User getByPasswordResetToken(String storeCode, String token);

    /**
     * Create or update a User
     */
    void saveOrUpdate(User user);

    List<User> listByStore(MerchantStore store);

    User findByStore(Long userId, String storeCode);

    User findByResetPasswordToken(String userName, String token, MerchantStore store);
}
