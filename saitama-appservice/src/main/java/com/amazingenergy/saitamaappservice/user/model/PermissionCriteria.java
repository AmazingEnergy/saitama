package com.amazingenergy.saitamaappservice.user.model;

import com.amazingenergy.core.Criteria;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class PermissionCriteria extends Criteria {
    private String permissionName;
    private Boolean available = null;
    private Set<Integer> groupIds;
    private List<String> availabilities;
}
