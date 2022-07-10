package com.amazingenergy.saitamajpa.user.repository;

import com.amazingenergy.saitamaappservice.user.model.UserCriteria;
import com.amazingenergy.saitamajpa.user.entity.UserEntity;
import org.springframework.data.domain.Page;

public interface CustomUserRepository {
    Page<UserEntity> listByCriteria(UserCriteria criteria);
}
