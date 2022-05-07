package com.amazingenergy.saitamaappservice.user.model;

import com.amazingenergy.core.Criteria;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCriteria extends Criteria {
    private String adminEmail;
    private String adminName;
    private boolean active = true;
}
