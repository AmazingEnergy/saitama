package com.amazingenergy.saitamajpa.tax;

import com.amazingenergy.saitamadomain.tax.domain.TaxClass;
import com.amazingenergy.saitamadomain.tax.domain.TaxRate;
import com.amazingenergy.saitamajpa.tax.entity.TaxClassEntity;
import com.amazingenergy.saitamajpa.tax.entity.TaxRateEntity;
import org.mapstruct.Mapper;

@Mapper
public interface TaxMapper {
    TaxClassEntity to(TaxClass source);

    TaxClass from(TaxClassEntity destination);

    TaxRateEntity to(TaxRate source);

    TaxRate from(TaxRateEntity destination);
}
