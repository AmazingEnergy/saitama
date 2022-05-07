package com.amazingenergy.saitamajpa.user.entity;

import com.amazingenergy.corejpa.audit.AuditListener;
import com.amazingenergy.corejpa.audit.Auditable;
import com.amazingenergy.corejpa.audit.EmbeddableAuditSection;
import com.amazingenergy.saitamadomain.user.domain.GroupType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(value = AuditListener.class)
@Table(name = "Group",
        indexes = {@Index(name = "SM_GROUP_GROUP_TYPE", columnList = "Type")})
public class GroupEntity implements Auditable {
    @Id
    @Column(name = "Id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Embedded
    private EmbeddableAuditSection auditSection;

    @Column(name = "Type")
    @Enumerated(value = EnumType.STRING)
    private GroupType groupType;

    @Column(name = "Name", unique = true)
    private String groupName;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "PermissionGroup",
            joinColumns = @JoinColumn(name = "GroupId"),
            inverseJoinColumns = @JoinColumn(name = "PermissionId"))
    private Set<PermissionEntity> permissions = new HashSet<>();
}
