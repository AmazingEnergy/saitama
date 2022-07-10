package com.amazingenergy.saitamajpa.user.repository;

import com.amazingenergy.saitamaappservice.user.model.PermissionCriteria;
import com.amazingenergy.saitamajpa.user.entity.PermissionEntity;
import org.springframework.data.domain.Page;

public interface CustomPermissionRepository {
    Page<PermissionEntity> listByCriteria(PermissionCriteria criteria);
}
