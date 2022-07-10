package com.amazingenergy.saitamadomain.tax.repository;

import com.amazingenergy.core.repository.EntityRepository;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import com.amazingenergy.saitamadomain.reference.domain.Country;
import com.amazingenergy.saitamadomain.reference.domain.Language;
import com.amazingenergy.saitamadomain.reference.domain.Zone;
import com.amazingenergy.saitamadomain.tax.domain.TaxClass;
import com.amazingenergy.saitamadomain.tax.domain.TaxRate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaxRateRepository extends EntityRepository<UUID, TaxRate> {
    List<TaxRate> listByStore(MerchantStore store);

    List<TaxRate> listByCountryZoneAndTaxClass(Country country, Zone zone,
                                               TaxClass taxClass, MerchantStore store, Language language);

    List<TaxRate> listByCountryStateProvinceAndTaxClass(Country country, String stateProvince,
                                                        TaxClass taxClass, MerchantStore store, Language language);

    Optional<TaxRate> getByCode(String code, MerchantStore store);

    Optional<TaxRate> getById(UUID id, MerchantStore store);

    List<TaxRate> listByStore(MerchantStore store, Language language);

    TaxRate saveOrUpdate(TaxRate taxRate);
}
