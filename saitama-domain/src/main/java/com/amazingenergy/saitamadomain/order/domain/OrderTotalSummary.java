package com.amazingenergy.saitamadomain.order.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class OrderTotalSummary implements Serializable {
    private BigDecimal subTotal;//one time price for items
    private BigDecimal total;//final price
    private BigDecimal taxTotal;//total of taxes
    private List<OrderTotal> totals;//all other fees (tax, shipping ....)
}
