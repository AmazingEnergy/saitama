package com.amazingenergy.saitamaappservice.user.repository;

import com.amazingenergy.saitamaappservice.user.model.UserCriteria;
import com.amazingenergy.saitamadomain.user.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserQueryService {
    Page<User> listByCriteria(UserCriteria criteria, Pageable pageable);
}
