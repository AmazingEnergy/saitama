package com.amazingenergy.saitamadomain.user.domain;

import com.amazingenergy.core.domain.AuditSection;
import com.amazingenergy.core.domain.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class Permission extends Entity<UUID, Permission> {
    private String permissionName;
    private AuditSection auditSection;

    public Permission() {
        super(UUID.randomUUID());
    }

    public Permission(String permissionName) {
        super(UUID.randomUUID());
        this.permissionName = permissionName;
    }
}
