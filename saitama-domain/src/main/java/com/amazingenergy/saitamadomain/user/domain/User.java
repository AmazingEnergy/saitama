package com.amazingenergy.saitamadomain.user.domain;

import com.amazingenergy.core.domain.AggregateRoot;
import com.amazingenergy.core.domain.AuditSection;
import com.amazingenergy.saitamadomain.reference.domain.Language;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class User extends AggregateRoot<UUID, User> {
    private String firstName;
    private String lastName;
    private String email;
    private Language defaultLanguage;

    private String question1;
    private String question2;
    private String question3;
    private String answer1;
    private String answer2;
    private String answer3;

    private Date lastAccess;
    private Date loginTime;

    private List<Group> groups = new ArrayList<Group>();
    private MerchantStore merchantStore;
    private boolean active = true;
    private AuditSection auditSection;

    public User() {
        super(UUID.randomUUID());
    }
}
