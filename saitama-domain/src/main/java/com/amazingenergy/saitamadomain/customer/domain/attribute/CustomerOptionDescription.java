package com.amazingenergy.saitamadomain.customer.domain.attribute;

import com.amazingenergy.saitamadomain.common.Description;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CustomerOptionDescription extends Description {
    private String customerOptionComment;
}
