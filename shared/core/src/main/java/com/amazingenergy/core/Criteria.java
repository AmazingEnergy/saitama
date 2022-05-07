package com.amazingenergy.core;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Criteria {
    private int startPage = 0;
    private int pageSize = 10;
    private String code;
    private String name;
    private String language;
    private String user;
    private String storeCode;
    private List<Integer> storeIds;
    private CriteriaOrderDirection direction = CriteriaOrderDirection.DESC;
    private String orderBy;
    private String search;
}
