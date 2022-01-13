package com.amazingenergy.saitamadomain.order.domain;

import com.amazingenergy.saitamadomain.payment.domain.CreditCardType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditCard {
    private CreditCardType cardType;
    private String ccOwner;
    private String ccNumber;
    private String ccExpires;
    private String ccCvv;

}
