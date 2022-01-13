package com.amazingenergy.saitamadomain.user.domain;

import com.amazingenergy.core.domain.AuditSection;
import com.amazingenergy.core.domain.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class Group extends Entity<UUID, Group> {
    private String groupName;
    private GroupType groupType;
    private Set<Permission> permissions = new HashSet<>();
    private AuditSection auditSection;

    public Group() {
        super(UUID.randomUUID());
    }

    public Group(String groupName) {
        super(UUID.randomUUID());
        this.groupName = groupName;
    }
}
