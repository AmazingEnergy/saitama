package com.amazingenergy.saitamadomain.reference.repository;

import com.amazingenergy.core.repository.EntityRepository;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import com.amazingenergy.saitamadomain.reference.domain.Language;

import java.util.*;

public interface LanguageRepository extends EntityRepository<UUID, Language> {
    Optional<Language> getByCode(String code);

    List<Language> getLanguages();

    Optional<Language> toLanguage(Locale locale);

    Language defaultLanguage();
}
