package com.amazingenergy.saitamajpa.user.entity;

import com.amazingenergy.corejpa.audit.AuditListener;
import com.amazingenergy.corejpa.audit.Auditable;
import com.amazingenergy.corejpa.audit.EmbeddableAuditSection;
import com.amazingenergy.saitamajpa.common.CredentialsReset;
import com.amazingenergy.saitamajpa.merchant.entity.MerchantStoreEntity;
import com.amazingenergy.saitamajpa.reference.entity.LanguageEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(value = AuditListener.class)
@Table(name = "User", uniqueConstraints =
@UniqueConstraint(columnNames = {"MerchantId", "Email"}))
public class UserEntity implements Auditable {
    @Id
    @Column(name = "UserId", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Embedded
    private EmbeddableAuditSection auditSection;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MerchantId", nullable = false)
    private MerchantStoreEntity merchantStore;

    @Column(name = "Email", length = 96, nullable = false)
    private String email;

    @Column(name = "FirstName")
    private String firstName;

    @Column(name = "LastName")
    private String lastName;

    @Column(name = "Active")
    private boolean active = true;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = LanguageEntity.class)
    @JoinColumn(name = "LanguageId")
    private LanguageEntity defaultLanguage;

    @Column(name = "Question1")
    private String question1;

    @Column(name = "Question2")
    private String question2;

    @Column(name = "Question3")
    private String question3;

    @Column(name = "Answer1")
    private String answer1;

    @Column(name = "Answer2")
    private String answer2;

    @Column(name = "Answer3")
    private String answer3;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LastAccess")
    private Date lastAccess;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LoginAccess")
    private Date loginTime;

    @Embedded
    private CredentialsReset credentialsResetRequest = null;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH})
    @JoinTable(name = "UserGroup",
            joinColumns = {@JoinColumn(name = "UserId", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "GroupId", nullable = false, updatable = false)})
    @Cascade({
            org.hibernate.annotations.CascadeType.DETACH,
            org.hibernate.annotations.CascadeType.LOCK,
            org.hibernate.annotations.CascadeType.REFRESH,
            org.hibernate.annotations.CascadeType.REPLICATE

    })
    private List<GroupEntity> groups = new ArrayList<GroupEntity>();
}
