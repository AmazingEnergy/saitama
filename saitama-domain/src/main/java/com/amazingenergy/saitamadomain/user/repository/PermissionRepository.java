package com.amazingenergy.saitamadomain.user.repository;

import com.amazingenergy.core.repository.EntityRepository;
import com.amazingenergy.saitamadomain.user.domain.Group;
import com.amazingenergy.saitamadomain.user.domain.Permission;

import java.util.List;
import java.util.UUID;

public interface PermissionRepository extends EntityRepository<UUID, Permission> {
    List<Permission> getByName();

    List<Permission> listPermission();

    Permission getById(Integer permissionId);

    List<Permission> getPermissions(List<Integer> groupIds);

    void deletePermission(Permission permission);

    void removePermission(Permission permission, Group group);
}
