package com.amazingenergy.saitamaappservice.order.model;

import com.amazingenergy.core.Criteria;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderCriteria extends Criteria {
    private String customerName = null;
    private String customerPhone = null;
    private String status = null;
    private Long id = null;
    private String paymentMethod;
    private Long customerId;
    private String email;
}
