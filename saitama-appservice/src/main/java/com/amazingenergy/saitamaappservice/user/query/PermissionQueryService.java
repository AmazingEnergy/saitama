package com.amazingenergy.saitamaappservice.user.query;

import com.amazingenergy.saitamaappservice.user.model.PermissionCriteria;
import com.amazingenergy.saitamadomain.user.domain.Permission;
import org.springframework.data.domain.Page;

public interface PermissionQueryService {
    Page<Permission> listByCriteria(PermissionCriteria criteria);
}
